package SystemBankowy;

class User {
    String NazwaUzytkownika;
    public Double StanKonta;
    String NrKonta;
    int IdKarty;
    int UserId;
    
    public void setNazwaUzytkownika(String name)
    {
        NazwaUzytkownika = name;
    }
    
    public String getNazwaUzytkownika()
    {
        return NazwaUzytkownika;
    }  
    
    public void setStanKonta(Double stankonta)
    {
        this.StanKonta = stankonta;
    }

    public Double getStanKonta()
    {
        return this.StanKonta;
    }    
    public void setNrKonta(String nrkonta)
    {
        this.NrKonta = nrkonta;
    }

    public String getNrKonta()
    {
        return this.NrKonta;
    }    
    public void setIdKarty(int idkarty)
    {
        this.IdKarty = idkarty;
    }

    public String getIdKarty()
    {
        return this.NazwaUzytkownika;
    }    
    public void setUserId(int userid)
    {
        this.UserId = userid;
    }

    public int getUserId()
    {
        return this.UserId;
    }           
}
