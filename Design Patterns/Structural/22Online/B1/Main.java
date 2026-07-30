interface NotificationSender{
    void send(String message);
}

class Email implements NotificationSender{
    @Override
    public void send(String message) {
        
        System.out.println("Notification sent through email");
    } 
}

class SMS implements NotificationSender{
    @Override
    public void send(String message) {
        
        System.out.println("Notification sent through sms");
    } 
}

class PushNotifaction implements NotificationSender{
    @Override
    public void send(String message) {
        
        System.out.println("Notification sent through PushNotification");
    } 
}

class Whatsapp implements NotificationSender{
    @Override
    public void send(String message) {
        
        System.out.println("Notification sent through whatsapp");
    } 
}


abstract class Notification{
    protected NotificationSender sender;
    public Notification(NotificationSender sender)
    {
        this.sender = sender;
    }
    abstract void notifyUser();
}

class OrderConfirmed extends Notification{

    public OrderConfirmed(NotificationSender sender) {
        super(sender);
    }

    @Override
    void notifyUser() {
        // TODO Auto-generated method stub
        System.out.println("your order has been confirmed");
    }
    
}

class PaymentFailed extends Notification{

    public PaymentFailed(NotificationSender sender) {
        super(sender);
    }

    @Override
    void notifyUser() {
        // TODO Auto-generated method stub
        System.out.println("Payment falied!");
    }
    
}

class Renewal extends Notification{

    public Renewal(NotificationSender sender) {
        super(sender);
    }

    @Override
    void notifyUser() {
        // TODO Auto-generated method stub
        System.out.println("Your subscription is renewed");
    }
    
}

class Main{
    public static void main(String[] args) {
        Notification notification1 = new OrderConfirmed(new Email());
        notification1.notifyUser();
        Notification notification2 = new Renewal(new SMS());
        notification2.notifyUser();
    }
}