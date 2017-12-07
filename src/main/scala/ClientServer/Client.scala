package ClientServer


import akka.actor.{Actor, ActorLogging}

class Client extends Actor with ActorLogging { // se chiede di essere astratta potrebbe volere qualche attributo inizializzato
          //(username: String, server:ActorRef)

  import Client._
  //storico => List

  /*def getUsername: String = {
    return this.username
  }
  */

  //var username : String
  //val system = ActorSystem("System")
  //val server = system.actorOf(Props[Server])
  //private[ClientServer] val system = ActorSystem.create("Attore_di_Sistema3")
  //private[ClientServer] val server = system.actorOf(Props.create(classOf[Server]), "Server")
  //private[ClientServer] val fabio = system.actorOf(Props.create(classOf[Client]), "Fabio")

  override def receive: Receive = {


    case Ti_Sei_Registrato()=>{
      println("Sei stato registrato!\nAdesso clicca su Accedi per proseguire!")
    }

    case Sei_già_Registrato()=>{
      println("Sei già registrato. Adesso clicca su Accedi per proseguire!")
    }

    case NuovaChat()=>{



    }

      /*
    case RegistrazioneClient()=>{
      server ! (username,context.self)
      println("Ti sei registrato!")
    }
    case InviaGruppo(mittente,messaggio,destinatario,gruppo)=>{
      invia(destinatario,messaggio)
      println("Messaggio inviato")
    }
    case ArchivioMessaggi(mittente,messaggio,destinatario,gruppo)=>{
      invia_archivio(mittente,messaggio,destinatario)
      val writeToFile = new FileWriter("messaggi_storico.txt")
      //writeToFile.write(s)
      writeToFile.close()
      println("Storico messaggi ottenuto")
    }*/
  }






  def CreaGruppo()={

  }

  def RicreaArchivio()={

  }

  def CancellaArchivio()={

  }

  def invia(username:String, messaggio: String): Unit ={
    //server ! (username,messaggio)
  }

  def invia_archivio(mittente: String,messaggio: String, destinatario:String): Unit ={
    //server !(mittente,messaggio,destinatario)
  }



}

object Client {
  final case class Ti_Sei_Registrato()
  final case class Sei_già_Registrato()
  final case class NuovaChat()
  //final case class InviaGruppo( mittente: String,messaggio: String, destinatario:String, gruppo:Client)
  //final case class ArchivioMessaggi(mittente: String,messaggio: String, destinatario:String, gruppo:Client )
  //final case class RegistrazioneClient()
}