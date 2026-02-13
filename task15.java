package project15;
class TicketBooking 
{
    private int tickets = 5;//shared resource

    public synchronized void bookTicket(String userName, int numberOfTickets) 
    {
        System.out.println(userName + " is trying to book " + numberOfTickets + " tickets.");
        if (tickets >= numberOfTickets) 
            {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tickets -= numberOfTickets;
            System.out.println(userName + " successfully booked " + numberOfTickets + " tickets.");
        } 
        else 
            {
            System.out.println("Sorry! Not enough tickets for " + userName);
        }

        System.out.println("Remaining tickets: " + tickets);
    }
}

//thread class
class UserThread extends Thread 
{
    TicketBooking booking;
    String userName;
    int tickets;
    UserThread(TicketBooking booking, String userName, int tickets) 
    {
        this.booking = booking;
        this.userName = userName;
        this.tickets = tickets;
    }

    public void run() 
    {
        booking.bookTicket(userName, tickets);
    }
}

//main class
public class task15 
{
    public static void main(String[] args) 
    {
        TicketBooking booking = new TicketBooking();
        UserThread t1 = new UserThread(booking, "User1", 3);
        UserThread t2 = new UserThread(booking, "User2", 3);
        UserThread t3 = new UserThread(booking, "User3", 1);
        t1.start();
        t2.start();
        t3.start();
    }
}
