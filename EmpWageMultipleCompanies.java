public class EmpWageMultipleCompanies {
    public final String company;
    public final int empRatePerHour;
    public final int numOfWorkingDays;
    public final int maxHoursPerMonth;
    public int totalEmpWage;


    public EmpWageMultipleCompanies(String company, int empRatePerHour,
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
class EmpWageBuilderArray{
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;

    private int numOfCompany = 0;
    private EmpWageMultipleCompanies[ ] companyEmpWageArray;

    public EmpWageBuilderArray(){
        companyEmpWageArray = new EmpWageMultipleCompanies[5];
    }

    private void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth){
        companyEmpWageArray[numOfCompany] = new EmpWageMultipleCompanies(company, empRatePerHour, numOfWorkingDays, maxHoursPerMonth);
        numOfCompany++;
    }

    private void computeEmpWage(){
        for (int i = 0; i< numOfCompany; i++){
            companyEmpWageArray[i].setTotalEmpWage(this.computeEmpWage(companyEmpWageArray[i]));
            System.out.println(companyEmpWageArray[i]);
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
        EmpWageBuilderArray empWageBuilderArray = new EmpWageBuilderArray();
        empWageBuilderArray.addCompanyEmpWage("DMart", 20, 2, 10);
        empWageBuilderArray.addCompanyEmpWage("Realiance", 10,4, 20);;
        empWageBuilderArray.computeEmpWage();
    }
}