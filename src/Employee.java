public class Employee {
    private String name;
    private int totalSales, salesPeriod;
    private double experienceMultiplier;

    public Employee(String name, int totalSales, int salesPeriod, double experienceMultiplier) {
        this.name = name;
        this.totalSales = totalSales;
        this.salesPeriod = salesPeriod;
        this.experienceMultiplier = experienceMultiplier;
    }

    public String getName() {
        return name;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public int getSalesPeriod() {
        return salesPeriod;
    }

    public Double getScore(boolean withExperienceMultiplier) {
        if (withExperienceMultiplier) {
            return totalSales/(double)salesPeriod * this.experienceMultiplier;
        }

        return totalSales/(double)salesPeriod;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Total sales: " + totalSales + "\n" +
                "Sales period: " + salesPeriod + "\n" +
                "Exp multiplier: " + experienceMultiplier;
    }
}
