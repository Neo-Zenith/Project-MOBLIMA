package models.staff;
public interface IStaffLogin extends IStaffAccess{
    public int login(int staffID, String password);
}