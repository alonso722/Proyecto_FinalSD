import java.util.*;//librerias

public class Servicios {//clase Servicios
    String id;
    String curp;
    String name;

    public Servicios( String id, String curp, String name){
        this.id=id;
        this.curp=curp;
        this.name=name;
    }

    public String getid() {
        return this.id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getcurp() {
        return this.curp;
    }

    public void setcurp(String curp) {
        this.curp = curp;
    }
    public String getname() {
        return this.name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void menu()
	{
        int i;
		String[] itemid = getid().split("-");
		String[] itemcurp = getcurp().split("-");
		String[] itemname = getname().split("-");
		for(i=0;i<=19;i++)
			{
				System.out.println("Cliente :"+itemid[i]+" / "+itemcurp[i]+" / "+itemname[i]);	
			}

	}
}
