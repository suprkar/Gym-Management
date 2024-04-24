public class ExecutionHandler extends Handler{
    private AdminOperations adminOps;

    public ExecutionHandler(AdminOperation adminOps){
        this.adminOps=adminOps;
    }

    @Override
    public void handleRequest(Request request){
        boolean updated=adminOps.updateAdminId(request.getCurrentId(),request.getNewId());
        if(updated){
            System.out.println("Admin ID updated successfully");
        }else{
            System.out.println("Failed to update Admin Id.");
        }
    }
}