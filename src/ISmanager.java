public class ISmanager extends administraor {
    private boolean ismanager;

    public boolean isIsmanager() {
        return ismanager;
    }

    public void setIsmanager(boolean ismanager) {
        this.ismanager = ismanager;
    }
    public void setMsg(String username,String password,boolean ismanager){
        setUsername(username);
        setPassword(password);
        setIsmanager(ismanager);
    }
}
