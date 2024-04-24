public class LoggingHandler extends Handler{
    @Override
    public void handleRequest(Request request){
        System.out.println("Logging request:"+ request);
        if(successor!=null){
            successor.handleRequest(request);
        }
    }
}