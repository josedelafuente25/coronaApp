package com.jose.coronaapp.objetos;

public class RegionApp {

    int IDREGION;
    String NOMBRECORTO;
    int T_CONF;
    int T_FALL;
    int T_REC;
    int N_ACT;

    public int getIDREGION() {
        return IDREGION;
    }

    public RegionApp(int IDREGION, String NOMBRECORTO, int t_CONF, int t_FALL, int t_REC, int n_ACT) {
        this.IDREGION = IDREGION;
        this.NOMBRECORTO = NOMBRECORTO;
        T_CONF = t_CONF;
        T_FALL = t_FALL;
        T_REC = t_REC;
        N_ACT = n_ACT;
    }

    public void setIDREGION(int IDREGION) {
        this.IDREGION = IDREGION;
    }

    public String getNOMBRECORTO() {
        return NOMBRECORTO;
    }

    public void setNOMBRECORTO(String NOMBRECORTO) {
        this.NOMBRECORTO = NOMBRECORTO;
    }

    public int getT_CONF() {
        return T_CONF;
    }

    public void setT_CONF(int t_CONF) {
        T_CONF = t_CONF;
    }

    public int getT_FALL() {
        return T_FALL;
    }

    public void setT_FALL(int t_FALL) {
        T_FALL = t_FALL;
    }

    public int getT_REC() {
        return T_REC;
    }

    public void setT_REC(int t_REC) {
        T_REC = t_REC;
    }

    public int getN_ACT() {
        return N_ACT;
    }

    public void setN_ACT(int n_ACT) {
        N_ACT = n_ACT;
    }


}
