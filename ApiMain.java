import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiMain {
    public final String company;
    public final int empRatePerHour;
    public final int numOfWorkingDays;
    public final int maxHoursPerMonth;
    public int totalEmpWage;


    public ApiMain(String company, int empRatePerHour,
                                    int numOfWorkingDays, int maxHoursPerMonth) {
        this.company = company;
        this.empRatePerHour = empRatePerHour;
        this.numOfWorkingDays = numOfWorkingDays;
        this.maxHoursPerMonth = maxHoursPerMonth;
    }

    public void setTotalEmpWage(int totalEmpWage) {
        this.totalEmpWage = totalEmpWage;
    }

    public String toString() {
        return "Total Emp Wage for Company: " + company + " is: " + totalEmpWage;
    }

}
class EmpWageBuilder{
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;

    private int numOfCompany = 0;
    private EmpWageMultipleCompanies[ ] companyEmpWage;

    public EmpWageBuilder(){
        companyEmpWage = new EmpWageMultipleCompanies[5];
    }

    private void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth){
        companyEmpWage[numOfCompany] = new EmpWageMultipleCompanies(company, empRatePerHour, numOfWorkingDays, maxHoursPerMonth);
        numOfCompany++;
    }

    private void computeEmpWage(){
        for (int i = 0; i< numOfCompany; i++){
            companyEmpWage[i].setTotalEmpWage(this.computeEmpWage(companyEmpWage[i]));
            System.out.println(companyEmpWage[i]);
        }
    }

    private int computeEmpWage(EmpWageMultipleCompanies companyEmpWage){
        int empHrs = 0, totalEmpHrs = 0, totalWorkingDays = 0;

        while (totalEmpHrs <= companyEmpWage.maxHoursPerMonth && totalWorkingDays < companyEmpWage.numOfWorkingDays){
            totalWorkingDays++;
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;
            switch (empCheck){
                case IS_PART_TIME:
                    empHrs = 4;
                    break;
                case IS_FULL_TIME:
                    empHrs = 8;
                default:
                    empHrs = 0;
            }
            totalEmpHrs += empHrs;
            System.out.println("Day#: " + totalWorkingDays + " Emp Hr: " +empHrs);
        }
        return totalEmpHrs * companyEmpWage.empRatePerHour;
    }
    public static void main(String[] args){
        var url = "https://github.com/ranjan1996kumar";
        //httprequest
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        EmpWageBuilder empWageBuilder = new EmpWageBuilder();
        empWageBuilder.addCompanyEmpWage("DMart", 20, 2, 10);
        empWageBuilder.addCompanyEmpWage("Realiance", 10,4, 20);;
        empWageBuilder.computeEmpWage();
    }
}