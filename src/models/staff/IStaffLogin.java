package models.staff;
public interface IStaffLogin extends IStaffAccess{
    public boolean login(int staffID, String password);
}