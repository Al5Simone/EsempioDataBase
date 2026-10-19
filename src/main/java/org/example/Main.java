import  org.example.DataBase;

import java.sql.SQLException;


void main() {
    DataBase dataBase = null;
    try{
       dataBase =  DataBase.getInstance();

    } catch (SQLException e){
        IO.println("Errore nella connessione del database: " + e.getMessage());
        System.exit(-1);
    }
    IO.println("DataBase Connesso");

    //dataBase.insertPiatto("Uramaki Miura", 5.3, 8);
    dataBase.insertPiatto(IO.readln("Insersci un Piatto: "), Double.parseDouble(IO.readln("Inserisci il prezzo: ")), Integer.parseInt(IO.readln("Inserisci la quantità: ")));

    dataBase.SelectAll("Sushi_Menu");
}

