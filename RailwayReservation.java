import java.util.*;
import java.io.*;
class Train{
    int Trainno;
    String Trainname;
    String source;
    String dest;
    int available;
    double fee;
    Train(int Trainno,String Trainname,String source,String dest,int available,double fee){
        this.Trainno=Trainno;
        this.Trainname=Trainname;
        this.source=source;
        this.dest=dest;
        this.available=available;
        this.fee=fee;
    }
}

class Ticket{
    int TicketId;
    String passengerName;
    int Trainno;
    int booked;
    Ticket(int TicketId,String passengerName,int Trainno,int booked){
        this.TicketId=TicketId;
        this.passengerName=passengerName;
        this.Trainno=Trainno;
        this.booked=booked;
    }
}
public class RailwayReservation {
    private static Map<Integer,Train>trainMap=new HashMap<>();
    private static List<Ticket>TicketList=new ArrayList<>();
    private static int TC=1;//Ticket counter
    
    public static void main(String[] args) {
          Scanner sc=new Scanner(System.in);
          int choice;
          trainMap.put(101,new Train(101,"Chennai Express","Chennai","trichy",20,750.0));
          trainMap.put(102,new Train(102,"Madhurai Express","Madhurai","trichy",40,450.0));
          System.out.println("\t..Railway Reservation System..");
          do{
        
              System.out.println("1.View Trains");
              System.out.println("2.Book Tickets");
              System.out.println("3.View Tickets");
              System.out.println("4.exit");
              System.out.println("Enter your choice: ");
              choice=sc.nextInt();
          
          switch(choice){
              case 1:
                  viewTrains();
                  break;
              case 2:
                  bookTickets(sc);
                  break;
              case 3:
                  viewTickets();
                  break;
              case 4:
                  System.out.println("Thank you for your visit!!");
                  break;
              default:
                  System.out.println("Please enter correct choice!!");
                  break;
          }
          }while(choice!=4);
          sc.close();

}
     public static void viewTrains(){
        System.out.println("!--Available Tains!--");
        for(Train train:trainMap.values()){
            System.out.printf("Train No: %d,Train Name: %s,Source: %s,Destination: %s,Available Seats:%d,Fare: %f\n",train.Trainno,train.Trainname,train.source,train.dest,train.available,train.fee);
        }
    }
    private static void bookTickets(Scanner sc){
         System.out.println("Book your Tickets!!");
         System.out.println("Enter Train no: ");
         int no=sc.nextInt();
         Train train=trainMap.get(no);
         if(train==null){
             System.out.println("No Tain available");
             return;
         }
         System.out.println("Enter passenger Name: ");
         String name=sc.next();
         System.out.println("Enter how many tickets do you want?");
         int seats=sc.nextInt();
         if(seats<=0 || seats>train.available){
             System.out.println("Insufficient amount");
             return;
         }
         train.available-=seats;
         Ticket ticket=new Ticket(TC++,name,no,seats);
         TicketList.add(ticket);
         System.out.println("Booking Completed Successfully!!");
    
         
         
     }
    private static void viewTickets(){
        if(TicketList.isEmpty()){
            System.out.println("No tickets booked yet!!");
            return;
        }
        for(Ticket ticket:TicketList){
            System.out.printf("Ticke id: %d\nPassenger Name: %s\nTrain no: %d\nSeats Booked:%d\n",ticket.TicketId,ticket.passengerName,ticket.Trainno,ticket.booked);
        }
    }
    
}

