/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer 
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    private int [] monthCounts;
    
    private int [] dayCounts;
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
        
        monthCounts = new int [13];
        
        dayCounts= new int [29];
    }
    
    /**
     * Analyze the number of times a file has been accessed.
     */
    public int numberOfAccesses()
    {
        int total =0;
        for (int hour=0; hour<hourCounts.length; hour++){
            total += hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Analyze the busiest hour.
     */
    
    public int busiestHour()
    {
       int max = hourCounts[0];
       int counter = 0;
       int index = 0;
       for(int hourCount : hourCounts){
           if(max < hourCount){ 
               max = hourCount;
               counter = index;          
            }
            index = index + 1;
       }
       return counter;
    }
    
    /**
     * Analyze the least busy hour
     */
    public int quietestHour()
    {
       int min = hourCounts[0];
       int counter = 0;
       
       for(int i=0; i<hourCounts.length; i++){
           if( min>= hourCounts[i] ){ 
               min = hourCounts[i];
               counter = i;          
            }
            
       }
       return counter;
    }
    
    /**
     * Analyze the busiest 2 hours
     */ 
    public int busiestHour2()
    {
       int max = hourCounts[0] + hourCounts[1];
       
       int counter = 0;
       int index = 0;
       for(int hourCount : hourCounts){
       if(max  < hourCount){ 
          max = hourCount;
          counter = index;          
       }
        index = index + 1;
      }
      return counter;
    }
    
    /**
     * Analyze busiest hour
     */
    public int busiestDay()
    {
       int max = dayCounts[0];
       int counter = 0;
       int index = 0;
       for(int dayCount : dayCounts){
           if(max < dayCount){ 
               max =dayCount;
               counter = index;          
            }
            index = index + 1;
       }
       return counter;
    }
    
    /**
     * Analyze the least busy day
     */ 
    public int quietestDay()
    {
       int min = dayCounts[0];
       int counter = 0;
       
       for(int i=1; i<dayCounts.length; i++){
           if( min>= dayCounts[i] ){ 
               min = dayCounts[i];
               counter = i;          
            }
            
       }
       return counter;
    }
    
    /**
     * Analyze the busiest month
     */ 
    public int busiestMonth()
    {
       int max = monthCounts[0];
       int counter = 0;
       int index = 0;
       for(int monthCount : monthCounts){
           if(max < monthCount){ 
               max =monthCount;
               counter = index;          
            }
            index = index + 1;
       }
       return counter;
    }

    /**
     * Analyze the least busy month
     */
     public int quietestMonth()
    {
       int min = monthCounts[0];
       int counter = 0;
       
       for(int i=1; i<monthCounts.length; i++){
           if( min>= monthCounts[i] ){ 
               min = monthCounts[i];
               counter = i;          
            }
            
       }
       return counter;
    }
    
    
    /**
     * Analyze the total of accesses per month
     */
    public int totalAccessesPerMonth()
    {
        int total =0;
        for (int month=0; month<monthCounts.length; month++){
            total += monthCounts[month];
        }
        return total;
    }
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    
     /**
     * Analyze the monthy access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    
     /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }


    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
