
/**
 * Objekt tega razreda predstavlja ulomek.
 */

public class Ulomek implements Comparable<Ulomek> {

    private long stevec;
    private long imenovalec;

    private Ulomek(long stevec, long imenovalec) {
        this.stevec = stevec;
        this.imenovalec = imenovalec;
    }

    /**
     * Vrne ulomek s podanim "stevcem in imenovalcem.
     */
    public static Ulomek ustvari(long stevec, long imenovalec) {
        if (imenovalec == 0) {
            throw new IllegalArgumentException("Imenovalec ne sme biti 0");
        }
        long delitelj = Long.signum(imenovalec) *
            gcd(Math.abs(stevec), Math.abs(imenovalec));
        return new Ulomek(stevec / delitelj, imenovalec / delitelj);
    }

    /**
     * Vrne ulomek s "stevcem `stevilo' in imenovalcem 1.
     */
    public static Ulomek ustvari(long stevilo) {
        return ustvari(stevilo, 1L);
    }

    /**
     * Vrne "stevec ulomka `this'.
     */
    public long vrniStevec() {
        return this.stevec;
    }

    /**
     * Vrne imenovalec ulomka `this'.
     */
    public long vrniImenovalec() {
        return this.imenovalec;
    }

    /**
     * Vrne deseti"sko vrednost ulomka `this'.  Na primer, deseti"ska vrednost
     * ulomka 2/5 zna"sa 0.4.
     */
    public double vrednost() {
        return ((double) this.stevec) / ((double) this.imenovalec);
    }

    /**
     * Vrne vsoto ulomka `this' in ulomka `ulomek'.
     */
    public Ulomek vsota(Ulomek ulomek) {
        long skupniImenovalec = lcm(this.imenovalec, ulomek.imenovalec);
        long d1 = skupniImenovalec / this.imenovalec;
        long d2 = skupniImenovalec / ulomek.imenovalec;
        return ustvari(this.stevec * d1 + ulomek.stevec * d2, skupniImenovalec);
    }

    /**
     * Vrne vsoto ulomka `this' in podanega celega "stevila.
     */
    public Ulomek vsota(long stevilo) {
        return this.vsota(ustvari(stevilo));
    }

    /**
     * Vrne nasprotno vrednost ulomka `this' (npr. 2/5 --> -2/5).
     */
    public Ulomek nasprotnaVrednost() {
        return ustvari(-this.stevec, this.imenovalec);
    }

    /**
     * Vrne razliko ulomka `this' in ulomka `ulomek'.
     */
    public Ulomek razlika(Ulomek ulomek) {
        return this.vsota(ulomek.nasprotnaVrednost());
    }

    /**
     * Vrne razliko ulomka `this' in podanega celega "stevila.
     */
    public Ulomek razlika(long stevilo) {
        return this.razlika(ustvari(stevilo));
    }

    /**
     * Vrne zmno"zek ulomka `this' in ulomka `ulomek'.
     */
    public Ulomek zmnozek(Ulomek ulomek) {
        return ustvari(this.stevec * ulomek.stevec, this.imenovalec * ulomek.imenovalec);
    }

    /**
     * Vrne zmno"zek ulomka `this' in podanega celega "stevila.
     */
    public Ulomek zmnozek(long stevilo) {
        return this.zmnozek(ustvari(stevilo));
    }

    /**
     * Vrne obratno vrednost ulomka `this' (npr. 2/5 --> 5/2).
     */
    public Ulomek obratnaVrednost() {
        return ustvari(this.imenovalec, this.stevec);
    }

    /**
     * Vrne koli"cnik ulomka `this' in ulomka `ulomek'.
     */
    public Ulomek kolicnik(Ulomek ulomek) {
        return this.zmnozek(ulomek.obratnaVrednost());
    }

    /**
     * Vrne koli"cnik ulomka `this' in podanega celega "stevila.
     */
    public Ulomek kolicnik(long stevilo) {
        return this.kolicnik(ustvari(stevilo));
    }

    /**
     * Vrne `true' natanko v primeru, "ce je ulomek `this' manj"si od ulomka
     * `ulomek'.
     */
    public boolean jeManjsiOd(Ulomek ulomek) {
        return (this.compareTo(ulomek) < 0);
    }

    /**
     * Vrne `true' natanko v primeru, "ce je ulomek `this' manj"si od podanega
     * celega "stevila.
     */
    public boolean jeManjsiOd(int stevilo) {
        return (this.compareTo(stevilo) < 0);
    }

    /**
     * Vrne
     *     1, "ce je ulomek `this' ve"cji od ulomka `ulomek';
     *    -1, "ce je ulomek `this' manj"si od ulomka `ulomek';
     *     0, "ce sta ulomka enaka.
     */
    @Override
    public int compareTo(Ulomek ulomek) {
        long skupniImenovalec = lcm(this.imenovalec, ulomek.imenovalec);
        long d1 = skupniImenovalec / this.imenovalec;
        long d2 = skupniImenovalec / ulomek.imenovalec;
        long s1 = this.stevec * d1;
        long s2 = ulomek.stevec * d2;
        return (s1 < s2) ? (-1) : ((s1 == s2) ? 0 : 1);
    }

    /**
     * Vrne
     *     1, "ce je ulomek `this' ve"cji od podanega celega "stevila;
     *    -1, "ce je ulomek `this' manj"si od podanega celega "stevila;
     *     0, "ce sta ulomek in "stevilo enaka.
     */
    public int compareTo(long stevilo) {
        return this.compareTo(ustvari(stevilo));
    }

    /**
     * Vrne `true' natanko v primeru, "ce podani objekt predstavlja enak
     * ulomek kot objekt `this'.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Ulomek)) {
            return false;
        }
        return (this.compareTo((Ulomek) obj) == 0);
    }

    /**
     * Vrne predstavitev ulomka `this' v obliki niza "stevec/imenovalec.
     */
    @Override
    public String toString() {
        if (this.stevec % this.imenovalec == 0) {
            return Long.toString(this.stevec / this.imenovalec);
        }
        return String.format("%d/%d", this.stevec, this.imenovalec);
    }

    /**
     * Vrne zgo"s"cevalno kodo ulomka `this'.
     */
    @Override
    public int hashCode() {
        long h = 17L * this.stevec + 31L * this.imenovalec;
        return (int) (h % 1_000_000_007);
    }

    /**
     * Vrne najve"cji skupni delitelj podanih pozitivnih celih "stevil.
     */
    public static long gcd(long a, long b) {
        while (b > 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    /**
     * Vrne najmanj"si skupni ve"ckratnik podanih pozitivnih celih "stevil.
     */
    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}