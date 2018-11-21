package Pattern0;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author Axel Mathieu
 * @description Create an actor and passing his reference to
 *				another actor by message.
 */
public class CreateAndReferenceByMessage {

	public static void main(String[] args) {
		// Instantiate system actor
		final ActorSystem system = ActorSystem.create("system");
		
		// Instantiate first and second actor
	    final ActorRef firstActor = system.actorOf(MyFirstActor.createActor(), "firstActor");
	    final ActorRef secondActor = system.actorOf(MySecondActor.createActor(), "secondActor");
	    
	    // Let secondActor know firstActor exist by sending him a message.
	    secondActor.tell("Hello world !", firstActor);
	    
	    // Default identity here is 'deadLetters' when
	    // no sender is told.
	    secondActor.tell("Hello world !", ActorRef.noSender());

	    // Be careful, main thread will never terminate.
	    // We wait 1 second before ending system (by default)
	    // But this is not the best solution.
	    try {
			waitBeforeTerminate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			system.terminate();
		}
	}

	public static void waitBeforeTerminate() throws InterruptedException {
		Thread.sleep(1000);
	}

}
