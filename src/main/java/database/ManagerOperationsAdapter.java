public class ManagerOperationsAdapter implements ManagerOperations{
    private AdminOperations adminOperations;

    public ManagerOperationsAdapter(AdminOperations adminOperations){
        this.adminOperations=adminOperations;
    }

    @Override
    public boolean updateManagerId(String currentId,String newId){
        return adminOperations.updateAdminId(currentId,newId);
    }
}