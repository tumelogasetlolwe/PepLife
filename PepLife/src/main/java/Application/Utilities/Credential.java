package Application.Utilities;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("ValidLoginData")
public class Credential {

    @ExcelCellName("Username")
    private String userName;


    @ExcelCellName("Password")
    private String password;

    public Credential() {
    }

    public Credential(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}