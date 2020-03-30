package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusOnNegatiivinen() {
        Varasto kayttokelvotonVarasto;
        kayttokelvotonVarasto = new Varasto(-10);
        assertEquals(0, kayttokelvotonVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toStringinTulostus() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void varastoonLaitetaanLiikaa() {
        varasto.lisaaVarastoon(50);
        // varasto on taynna, ei vapaata tilaa
        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonLisataanNegatiinenMaara() {
        varasto.lisaaVarastoon(-50);
        assertEquals(10.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaOtetaanEnemmanKuinSiellaOn() {
        double saatuMaara = varasto.otaVarastosta(50);
        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void varastostaOtetaanNegatiinenMaara() {
        double saatuMaara = varasto.otaVarastosta(-50);
        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void konstruktoriJolleAnnetaanTilavuusJaAlkusaldo() {
        Varasto varasto2;
        varasto2 = new Varasto(10, -2);
        assertEquals(0.0, varasto2.getSaldo(), vertailuTarkkuus);
        
        varasto2 = new Varasto(-10, 0);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
        
        varasto2 = new Varasto(10, 5);
        assertEquals(5.01, varasto2.getSaldo(), vertailuTarkkuus);  
    }
}
