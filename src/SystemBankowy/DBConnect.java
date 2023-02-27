package SystemBankowy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    static boolean pierwszy = true;
    public static Connection polacz() {
        String baza = "SystemBankowy";
        Connection polaczenie = null;
        try {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:"+baza+".db");
            System.out.println("Połączyłem się z bazą "+baza);
        } catch (Exception e) {
            System.err.println("Błąd w połączeniu z bazą: \n" + e.getMessage());
            return null;
        }
        return polaczenie;
    }
    
       public static void stworzTabele(Connection polaczenie) {
        Statement stat = null;
        try {
            stat = polaczenie.createStatement();
            String tabelaSQL = "CREATE TABLE IF NOT EXISTS Users" 
                    + " (ID INTEGER PRIMARY KEY  AUTOINCREMENT    NOT NULL,"
                    + " Imie         CHAR(50)    NOT NULL, "
                    + " Nazwisko        CHAR(50)     NOT NULL, "
                    + " Haslo        CHAR(50)     NOT NULL, "
                    + " Saldo             CHAR(50), "
                    + " IDKart              INT, "
                    + " NrKonta             INT, "
                    + " UWAGI          TEXT)";
            String tabelaSQL2 = "CREATE TABLE IF NOT EXISTS Cards"
                    + " (ID INTEGER PRIMARY KEY  AUTOINCREMENT    NOT NULL, "
                    + " OwnerID         CHAR(50)    NOT NULL, "
                    + " CardNumber        TEXT(50)     NOT NULL, "
                    + " Date        CHAR(50)     NOT NULL, "
                    + " Active        CHAR(50)     NOT NULL)";
            String tabelaSQLLog = "CREATE TABLE IF NOT EXISTS Logs"
                    + " (ID INTEGER PRIMARY KEY  AUTOINCREMENT    NOT NULL, "
                    + " OwnerID         CHAR(50)    NOT NULL, "
                    + " Action        TEXT(100)     NOT NULL, "
                    + " Date        text(50)     NOT NULL)";
            String tabelaSQLu1 = "INSERT INTO Users (ID, Imie, Nazwisko, Haslo, Saldo, IDKart, NRKonta, Uwagi) VALUES ('1', 'Mateusz', 'Łyczek', '123', '1000', '1', '19 2490 1044 0000 3200 9401 0001', 'Twórca')";
            String tabelaSQLu2 = "INSERT INTO Users (ID, Imie, Nazwisko, Haslo, Saldo, IDKart, NRKonta, Uwagi) VALUES ('2', 'Piotr', 'Rawski', '123', '1000', '2', '19 2490 1044 0000 3200 9402 0002', 'Twórca')";
            String tabelaSQLu3 = "INSERT INTO Users (ID, Imie, Nazwisko, Haslo, Saldo, IDKart, NRKonta) VALUES ('3', 'User1', 'Testowy', 'User1', '1000', '3', '19 2490 1044 0000 3200 9403 0003')";
            String tabelaSQLu4 = "INSERT INTO Users (ID, Imie, Nazwisko, Haslo, Saldo, IDKart, NRKonta) VALUES ('4', 'User2', 'Testowy', 'User2', '1000', '4', '19 2490 1044 0000 3200 9404 0004')";
            String tabelaSQLu11 = "INSERT INTO Cards (ID, OwnerID, CardNumber, Date, Active) VALUES ('1', '1', '1787675599890001', '03/22', 'Aktywna')";
            String tabelaSQLu22 = "INSERT INTO Cards (ID, OwnerID, CardNumber, Date, Active) VALUES ('2', '2', '1787675599890002', '03/22', 'Aktywna')";
            String tabelaSQLu33 = "INSERT INTO Cards (ID, OwnerID, CardNumber, Date, Active) VALUES ('3', '3', '1787675599890003', '03/22', 'Aktywna')";
            String tabelaSQLu44 = "INSERT INTO Cards (ID, OwnerID, CardNumber, Date, Active) VALUES ('4', '4', '1787675599890004', '03/22', 'Aktywna')";            
            
            stat.executeUpdate(tabelaSQL);
            stat.executeUpdate(tabelaSQL2);
            stat.executeUpdate(tabelaSQLu1);
            stat.executeUpdate(tabelaSQLu2);
            stat.executeUpdate(tabelaSQLu3);
            stat.executeUpdate(tabelaSQLu4);
            stat.executeUpdate(tabelaSQLu11);
            stat.executeUpdate(tabelaSQLu22);
            stat.executeUpdate(tabelaSQLu33);
            stat.executeUpdate(tabelaSQLu44);
            stat.executeUpdate(tabelaSQLLog);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            //System.out.println("Nie mogę stworzyć tabeli" + e.getMessage());
        }
    }
       
       /*public static void DodajUsera(String imie, String nazwisko, String haslo) {
        Connection polaczenie = polacz();
        Statement stat = null;
        String maxnr = null;
        try {
            stat = polaczenie.createStatement();
            ResultSet result = stat.executeQuery("SELECT NrKonta FROM Users ORDER BY NrKonta DESC LIMIT 1");
                maxnr = result.getString("NrKonta");
                System.out.println(result.getString("NrKonta"));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            String wynik = maxnr.replaceAll("\\s+", "");
            System.out.println("Wynik: "+wynik);
            Home.maxxnr = Long.parseLong(wynik);
            System.out.println("Home.maxxnr: "+Home.maxxnr);
            stat = polaczenie.createStatement();
            
            String tabelaSQL = "INSERT INTO Users (Imie, Nazwisko, Haslo, NrKonta, Saldo) VALUES ('"+imie+"', '"+nazwisko+"', '"+haslo+"', '"+Home.maxxnr+"', '1000')";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println("Nie mogę stworzyć " + e.getMessage());
        } catch (NullPointerException ex){
            
        }
    }*/
       
        public static Boolean SprawdzHaslo(String imie, String haslo) {
        Connection polaczenie = polacz();
        Statement stat = null;
        Boolean zgodne = false;
        String imiesql, haslosql;
        try {
            stat = polaczenie.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Users WHERE Imie = '"+imie+"'");
            //int id;
                //id = result.getInt("ID");
                imiesql = result.getString("Imie");
                haslosql = result.getString("Haslo");
                
            //System.out.println(imiesql);
            result.close();
            stat.close();
            polaczenie.close();
                
        } catch (SQLException e) {
            //e.printStackTrace();
            zgodne = false;
            return zgodne;
        }
        zgodne = imie.equals(imiesql)&&haslo.equals(haslosql);
        return zgodne;
        
    }
        
    public static void SprawdzDane(String imie) {
        Connection polaczenie = polacz();
        Statement stat = null;
        //Boolean zgodne = false;
        String imiesql = null, nrkontasql = null, nazwiskosql = null;
        int idsql = 0, idkartysql = 0;
        Double stankontasql = null;
        try {
            stat = polaczenie.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Users WHERE Imie = '"+imie+"'");
                idsql = result.getInt("ID");
                imiesql = result.getString("Imie");
                nazwiskosql = result.getString("Nazwisko");
                idkartysql = result.getInt("IDKart");
                nrkontasql = result.getString("NrKonta");
                stankontasql = result.getDouble("Saldo");
                
                        
            result.close();
            stat.close();
            polaczenie.close(); 

        
        Home.NazwaUzytkownika=imiesql;
        Home.UserId=idsql;
        Home.Nazwisko=nazwiskosql;
        Home.IdKarty=idkartysql;
        Home.NrKonta=nrkontasql;
        Home.StanKonta= (double)Math.round(stankontasql * 100) / 100 ;
        //System.out.println(User.getNazwaUzytkownika() + "testyyyyyy");
        //SystemBankowy.User.NazwaUzytkownika;
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
        
    public static void Wplata(int id, double kwota, double StanKonta) {
        Connection polaczenie = polacz();
        Statement stat = null;
        try {
            stat = polaczenie.createStatement();;
            String tabelaSQL = "UPDATE Users SET Saldo = '"+(StanKonta+kwota)+"' where id ='"+id+"'";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public static void Wyplata(int id, double kwota, double StanKonta) {
        Connection polaczenie = polacz();
        Statement stat = null;
        if(StanKonta >= kwota){
        try {
            stat = polaczenie.createStatement();;
            String tabelaSQL = "UPDATE Users SET Saldo = '"+(StanKonta-kwota)+"' where id ='"+id+"'";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
        else{
            Home.jLabel14.setText("Nie posiadasz wystarczającej ilości środków na koncie.");
        }
    }
    public static void GeneratorKart() {
        Connection polaczenie = polacz();
        Statement stat = null;
        long maxnr = 0;
        try {
            stat = polaczenie.createStatement();
            ResultSet result = stat.executeQuery("SELECT CardNumber FROM Cards  ORDER BY id DESC LIMIT 1");
                maxnr = result.getLong("CardNumber");
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        try {
            //stat = polaczenie.createStatement();
            Home.maxxnr = maxnr+1;
            String tabelaSQL = "INSERT INTO Cards (OwnerID, CardNumber, Date, Active) VALUES ('"+Home.UserId+"', '"+Home.maxxnr+"', '03/25', 'Aktywna')";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
            DBConnect.Logi("Dodano kartę nr: "+Home.maxxnr);

        } catch (SQLException e) {
            System.out.println("Nie mogę dodać karty - " + e.getMessage());
        }
    }  

    public static void Karty() {
        Connection polaczenie = polacz();
        Statement stat = null;
        long maxnr = 0;
        try {
            stat = polaczenie.createStatement();
            ResultSet wynik = stat.executeQuery("SELECT * FROM Cards where OwnerID== '"+Home.UserId+"'");
                //Home.Karty = new long[2][2];
            Home.ileKart=0;
            while (wynik.next()) {
                Home.idKarty.add(wynik.getString("ID"));
                Home.idWlasciciela.add(wynik.getString("OwnerID"));
                Home.nrKarty.add(wynik.getString("CardNumber"));
                Home.dataWaznosci.add(wynik.getString("Date"));
                Home.statusAktywnosci.add(wynik.getString("Active"));
                Home.ileKart++;
            }
            wynik.close();
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void UsunKarte(String CardNumber) {
        Connection polaczenie = polacz();
        Statement stat = null;
        if(Home.ileKart > 1){
        try {
            stat = polaczenie.createStatement();
            String tabelaSQL = "DELETE FROM `Cards` WHERE `CardNumber`='"+CardNumber+"'";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println("Nie mogę skasować karty" + e.getMessage());
        }
        Karty();
        }
        else Home.jLabel14.setText("Nie można skasować jedynej karty.");
        
    }

    public static void PlatnoscKarta(String CardNumber, double kwota) {
        Connection polaczenie = polacz();
        Statement stat = null;
        SprawdzDane(Home.NazwaUzytkownika);
        
        if(Home.StanKonta >= kwota){
        String idUK = null;
        try {
            stat = polaczenie.createStatement();
            try (ResultSet idFromCard = stat.executeQuery("SELECT OwnerID FROM Cards where CardNumber == "+CardNumber+"")) {
                idUK = idFromCard.getString("OwnerID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            stat = polaczenie.createStatement();
            String tabelaSQL = "UPDATE Users SET Saldo = '"+(Home.StanKonta-kwota)+"' where id ='"+idUK+"'";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
        else{
            Home.jLabel14.setText("Nie posiadasz wystarczającej ilości środków na koncie.");
        }
    }
        
    public static void UserList() {
        Connection polaczenie = polacz();
        Statement stat = null;
        try {
            stat = polaczenie.createStatement();
            ResultSet wynik = stat.executeQuery("SELECT ID, Imie, Nazwisko, NrKonta FROM Users ");
            while (wynik.next()) {
                
                Home.idUserow.add(wynik.getString("ID"));
                Home.imionaUserow.add(wynik.getString("Imie"));
                Home.nazwiskaUserow.add(wynik.getString("Nazwisko"));
                Home.nrKontUserow.add(wynik.getString("NrKonta"));
            }
            wynik.close();
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    public static void WykonajPrzelew(String doKogo, double kwota) {
        Connection polaczenie = polacz();
        Statement stat = null;
        SprawdzDane(Home.NazwaUzytkownika);
        
        if(Home.StanKonta >= kwota){
            int stan_odbiorcy = 0;
            try {
                stat = polaczenie.createStatement();
                ResultSet result = stat.executeQuery("SELECT Saldo FROM Users WHERE NrKonta='"+doKogo+"'");
                stan_odbiorcy = result.getInt("Saldo");
                result.close();
                stat.close();
                stat = polaczenie.createStatement();
                String PobierzSQL = "UPDATE Users SET Saldo = '"+(Home.StanKonta-kwota)+"' where id ='"+Home.UserId+"'";
                String DodajSQL = "UPDATE Users SET Saldo = '"+(stan_odbiorcy+kwota)+"' WHERE NrKonta='"+doKogo+"'";
                //System.out.println(tabelaSQL);
                
                stat.executeUpdate(PobierzSQL);
                stat.executeUpdate(DodajSQL);
                stat.close();
                
                polaczenie.close();
                Home.jLabel23.setText("Pomyślnie wykonano przelew");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            Home.jLabel14.setText("Nie posiadasz wystarczającej ilości środków na koncie.");
        }
    }
    public static void Logi(String Tekst) {
        Connection polaczenie = polacz();
        Statement stat = null;
        
        try {
            stat = polaczenie.createStatement();;
            String tabelaSQL = "INSERT INTO Logs (OwnerID, Date, Action) VALUES ('"+Home.UserId+"', '"+Home.DataiCzas+"', '"+Tekst+"')";
            System.out.println(tabelaSQL);
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

            ListaLogow();
    }

    
    public static void ListaLogow() {
        Connection polaczenie = polacz();
        Statement stat = null;
        for(int i=0; i<Home.showLogs.size(); i++)
            Home.showLogs.remove(i);
        
        try {
            Home.showLogs.clear();
            stat = polaczenie.createStatement();
            ResultSet wynik = stat.executeQuery("SELECT * FROM Logs WHERE OwnerID = '"+Home.UserId+"' ORDER BY ID DESC LIMIT 10");
            while (wynik.next()) {
                String format = "Data: "+wynik.getString("Date")+" | "+wynik.getString("Action")+"";
                
                Home.showLogs.add(format);
                //Home.ileLogow++;
            }
            wynik.close();
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        
    }
}
