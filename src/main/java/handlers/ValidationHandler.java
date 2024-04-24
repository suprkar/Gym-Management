public class ValidationHandler extends Handler{
    @Override
    public void handlerequest(Request request){
        if(request.isValid()){
            System.out.println("Validation successful");
            if(successor!=null){
                successor.handleRequest(request);
            }
        }else{
            System.out.println("Validation failed");
        }
    }
}