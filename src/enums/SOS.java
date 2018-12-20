package enums;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 20/12/2018<br/>
 * Time: 10:35<br/>
 * To change this template use File | Settings | File Templates.
 */
public enum SOS { //SOS enum met fields kan gebruikt worden bij eventueel gebruik van prioriteiten

    SOS_BRAND (1),
    SOS_ZINKEN (2),
    SOS_PANNE (3),
    SOS_NOOD (4),
    ALLES_OK (5);

    private final int sosCode;

    SOS(int sosCode) {
        this.sosCode = sosCode;
    }

    public int getLevelCode() {
        return this.sosCode;
    }

}

