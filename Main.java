import java.util.*;

//Creating a class to store the data
class Movie implements Comparable<Movie>{
    int startDate;
    int endDate;
    String movieName;
    
    //Constructor
    public Movie(int startDate,int endDate,String movieName)
    {
        this.startDate=startDate;
        this.endDate=endDate;
        this.movieName=movieName;
    }
    
    //Overriding compareTo function, to sort the List in ascending order, based on the endDate
    public int compareTo(Movie mov)
    {
        return this.endDate-mov.endDate;
    }
}


public class Main
{
    
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 

        //Taking input in the form of String. All the values in each lines are seperated by a comma(,)
        String startDateInput=sc.nextLine(); // Line 1: Start date of the movie
        String endDateInput=sc.nextLine(); // Line 1: End date of the movie
        String movieNameInput=sc.nextLine(); // Line 1: Name of the movie

        //Storing each value in an array 
	    String start[]  = startDateInput.split(","); 
        String end[] =  endDateInput.split(","); 
        String movie[] = movieNameInput.split(","); 
        
        List<Movie> movieList=new ArrayList<Movie>();  // List to store all the movies
        List<Movie> moviesToDo=new ArrayList<Movie>(); // List of movies, that the actor should work in

        for(int i=0;i<start.length;i++)
        {
            Movie mov = new Movie(getDateNumber(start[i]), getDateNumber(end[i]), movie[i]);
            movieList.add(mov);
        }
        Collections.sort(movieList); // Sorting the list of movies based on end date
        
        //Choosing the first movie
        moviesToDo.add(movieList.get(0));
        int endDateUpdate = movieList.get(0).endDate;
        
        for (int i=1; i<movieList.size();i++)
        {
            //If the current start date is greater than the last choosen end date
            if(movieList.get(i).startDate>endDateUpdate)
            {
                endDateUpdate=movieList.get(i).endDate;
                moviesToDo.add(movieList.get(i));
            }
        }
        int noOfMovies=moviesToDo.size();

        System.out.println("The movie actor should work in the below "+noOfMovies+" movies which will help them earn "+noOfMovies+" Crore rupees.");
        for(Movie mov:moviesToDo)
            System.out.println(mov.movieName);
	}

    //Function to convert the date to the number of days until that date.
    public static int getDateNumber(String date)
    {
        //Map to store the no. of days till the previous Month
        Map<String,Integer> monthMap=new HashMap<String,Integer>();
        
        monthMap.put("Jan",0);  // No previous month, Hence 0
        monthMap.put("Feb",31); // Till January - 31 days
        monthMap.put("Mar",59); // Till February - (31+28) days
        monthMap.put("Apr",90); // Till March - (31+28+31) days
        monthMap.put("May",120);
        monthMap.put("Jun",151);
        monthMap.put("Jul",181);
        monthMap.put("Aug",212);
        monthMap.put("Sep",243);
        monthMap.put("Oct",273);
        monthMap.put("Nov",304);
        monthMap.put("Dec",334);

        String[] dateArr=date.split(" ");//1st index will store the date and 2nd index will have the month
        int convertedDate=Integer.valueOf(dateArr[0])+monthMap.get(dateArr[1]);
        return convertedDate;
    }
}
