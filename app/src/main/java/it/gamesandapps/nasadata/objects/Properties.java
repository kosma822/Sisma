package it.gamesandapps.nasadata.objects;


public class Properties {

    private Double mag;
    private String place;
    private long time;
    private Integer updated;
    private Integer tz;
    private String url;
    private String detail;
    private Integer felt;
    private Double cdi;
    private Object mmi;
    private Object alert;
    private String status;
    private Integer tsunami;
    private Integer sig;
    private String net;
    private String code;
    private String ids;
    private String sources;
    private String types;
    private Object nst;
    private Double dmin;
    private Double rms;
    private Integer gap;
    private String magType;
    private String type;
    private String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public Properties() {
    }

    /**
     *
     * @param detail
     * @param type
     * @param net
     * @param tsunami
     * @param sources
     * @param title
     * @param time
     * @param updated
     * @param mag
     * @param place
     * @param types
     * @param status
     * @param alert
     * @param ids
     * @param rms
     * @param code
     * @param url
     * @param magType
     * @param mmi
     * @param cdi
     * @param tz
     * @param felt
     * @param nst
     * @param dmin
     * @param sig
     * @param gap
     */
    public Properties(Double mag, String place, Integer time, Integer updated, Integer tz, String url, String detail, Integer felt, Double cdi, Object mmi, Object alert, String status, Integer tsunami, Integer sig, String net, String code, String ids, String sources, String types, Object nst, Double dmin, Double rms, Integer gap, String magType, String type, String title) {
        super();
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.updated = updated;
        this.tz = tz;
        this.url = url;
        this.detail = detail;
        this.felt = felt;
        this.cdi = cdi;
        this.mmi = mmi;
        this.alert = alert;
        this.status = status;
        this.tsunami = tsunami;
        this.sig = sig;
        this.net = net;
        this.code = code;
        this.ids = ids;
        this.sources = sources;
        this.types = types;
        this.nst = nst;
        this.dmin = dmin;
        this.rms = rms;
        this.gap = gap;
        this.magType = magType;
        this.type = type;
        this.title = title;
    }

    public Double getMag() {
        return mag;
    }

    public void setMag(Double mag) {
        this.mag = mag;
    }

    public Properties withMag(Double mag) {
        this.mag = mag;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Properties withPlace(String place) {
        this.place = place;
        return this;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Properties withTime(Integer time) {
        this.time = time;
        return this;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Properties withUpdated(Integer updated) {
        this.updated = updated;
        return this;
    }

    public Integer getTz() {
        return tz;
    }

    public void setTz(Integer tz) {
        this.tz = tz;
    }

    public Properties withTz(Integer tz) {
        this.tz = tz;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Properties withDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public Integer getFelt() {
        return felt;
    }

    public void setFelt(Integer felt) {
        this.felt = felt;
    }

    public Properties withFelt(Integer felt) {
        this.felt = felt;
        return this;
    }

    public Double getCdi() {
        return cdi;
    }

    public void setCdi(Double cdi) {
        this.cdi = cdi;
    }

    public Properties withCdi(Double cdi) {
        this.cdi = cdi;
        return this;
    }

    public Object getMmi() {
        return mmi;
    }

    public void setMmi(Object mmi) {
        this.mmi = mmi;
    }

    public Properties withMmi(Object mmi) {
        this.mmi = mmi;
        return this;
    }

    public Object getAlert() {
        return alert;
    }

    public void setAlert(Object alert) {
        this.alert = alert;
    }

    public Properties withAlert(Object alert) {
        this.alert = alert;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Properties withStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getTsunami() {
        return tsunami;
    }

    public void setTsunami(Integer tsunami) {
        this.tsunami = tsunami;
    }

    public Properties withTsunami(Integer tsunami) {
        this.tsunami = tsunami;
        return this;
    }

    public Integer getSig() {
        return sig;
    }

    public void setSig(Integer sig) {
        this.sig = sig;
    }

    public Properties withSig(Integer sig) {
        this.sig = sig;
        return this;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public Properties withNet(String net) {
        this.net = net;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Properties withCode(String code) {
        this.code = code;
        return this;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Properties withIds(String ids) {
        this.ids = ids;
        return this;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public Properties withSources(String sources) {
        this.sources = sources;
        return this;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Properties withTypes(String types) {
        this.types = types;
        return this;
    }

    public Object getNst() {
        return nst;
    }

    public void setNst(Object nst) {
        this.nst = nst;
    }

    public Properties withNst(Object nst) {
        this.nst = nst;
        return this;
    }

    public Double getDmin() {
        return dmin;
    }

    public void setDmin(Double dmin) {
        this.dmin = dmin;
    }

    public Properties withDmin(Double dmin) {
        this.dmin = dmin;
        return this;
    }

    public Double getRms() {
        return rms;
    }

    public void setRms(Double rms) {
        this.rms = rms;
    }

    public Properties withRms(Double rms) {
        this.rms = rms;
        return this;
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    public Properties withGap(Integer gap) {
        this.gap = gap;
        return this;
    }

    public String getMagType() {
        return magType;
    }

    public void setMagType(String magType) {
        this.magType = magType;
    }

    public Properties withMagType(String magType) {
        this.magType = magType;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties withType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Properties withTitle(String title) {
        this.title = title;
        return this;
    }

}