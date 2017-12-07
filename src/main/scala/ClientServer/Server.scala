package ClientServer

import java.io.FileWriter

import akka.actor.{Actor, ActorRef}

import scala.io.Source

class Server extends Actor{

  import Client._
  import Server._

  var messaggi = List[(String)]()
  var utenti = List[(String ,String, String)]()
  var gruppi=List[(List[String], List[ActorRef])]()

  var stringa :String = ""
  var c : Int = 0

  var prova : Boolean = false


  //val system2 = ActorSystem.create("Attore_di_Sistema2")
  //private val server = system2.actorOf(Props.create(classOf[Server]), "Server")
  //private val nino = system2.actorOf(Props.create(classOf[Client]), "Nino")

  override def receive: Receive = {

    case Registrazione(id, user, pwd,server) =>

      var buf :String=""
      buf =Source.fromFile("Lista_Utenti_Registrati.txt").bufferedReader().readLine()
      var splitted:Array[String] = buf.split(";")
      var splitted2:Array[String] = buf.split("-")

      while (c <= ((splitted.length)-1)) {
        buf = splitted{c}
        splitted2 = buf.split("-")
        //splitint = splitted2{0}.asInstanceOf[Int]
        utenti= ((splitted2{0},splitted2{1} ,splitted2{2} ) :: utenti)
        //println("" + splitted {c})
        c+=1
      }
      prova = false
      utenti.foreach( x=>
        if (x==(id,user,pwd) == true)
          prova = true
      )

      if ( prova == false){ // controlla se l'utente è già presente nel "server"
        stringa = (id+"-"+user+"-"+pwd+"-;")
        var writeToFile = new FileWriter("Lista_Utenti_Registrati.txt",true)
        writeToFile.append(stringa)
        writeToFile.close()
        utenti= (id,user,pwd ) :: utenti
        server ! Ti_Sei_Registrato() // Il server invia un messaggio attraverso gli attori "di tipo client(o che fa riferimento alla classe client)"
      }
      else
        server ! Sei_già_Registrato() // Il server invia un messaggio attraverso gli attori "di tipo client(o che fa riferimento alla classe client)"

// FINE CASE REGISTRAZIONE

    case CreaGruppo(utenti,attori) => {



      //creare i gruppi come una lista di string che sarebbe
      //username o id del gruppo
      //e un numero variabile di actor ref in modo da avere piu attori


      gruppi = (utenti,attori):: gruppi
      val writeToFile = new FileWriter("gruppi.txt")
      //utenti.foreach(x=> s+=x._1 +"-")
      writeToFile.write(stringa)
      writeToFile.close()
      println("Gruppo creato!")
    }

    /*case Invia(mittente,messaggio,destinatario)=>{
      if (utenti contains(destinatario)){
        invia(destinatario,messaggio)
      }else{
        print("Non ho trovato l'utente !")
      }
      val writeToFile = new FileWriter("messaggi.txt")
      writeToFile.write(s)
      writeToFile.close()
      println("Messaggio inviato")
    }

    case ChiediMessaggi(mittente,messaggio,destinatario)=> {
      if (utenti contains (messaggi))
        invia_messaggi(mittente, messaggio, destinatario)
      val writeToFile = new FileWriter("messaggi_storico.txt")
      writeToFile.write(s)
      writeToFile.close()
      println("Storico messaggi ottenuto")
    }*/
  }

  //invia i messaggi
/*  def invia(username:String, messaggio: String): Unit ={
    utenti.foreach(x =>if (x._1 equals(username)) x._2 ! messaggio)
  }

  //invia la storia delle conversazioni
  def invia_messaggi(mittente: String,messaggio: String, destinatario:String): Unit ={
    utenti.foreach(x =>if (x._1 equals(mittente)) x._2 ! messaggio)
  }*/


}




object Server {

  final case class Registrazione(id:String, username: String, password: String, server: ActorRef)
  final case class CreaGruppo (utenti: List[(String)], attori: List[ActorRef])
  final case class Invia ( mittente: String,messaggio: String, destinatario:String)
  final case class ChiediMessaggi(mittente: String,messaggio: String, destinatario:String )
}


/*    COSE CHE POSSONO SERVIRE


Methods	Description
head	This method returns the first element of a list. POSSO SFRUTTARLO PER USARE SOLO L'ID
tail	This method returns a list consisting of all elements except the first. POSSO SFRUTTARLO PER STAMPARE L'UTENTE SENZA L'ID
isEmpty	This method returns true if the list is empty otherwise false.


 */


