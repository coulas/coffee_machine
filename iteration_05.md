# Fifth iteration - Running out
The users of the coffee machine are complaining that there is often shortages of water and/or milk. It takes weeks before the machine is refilled.

Your product owner wants to you to take advantage of the machine capabilities to inform the user that there is a shortage and to send a email notification to the company so that they can come and refill the machine.

# Use cases
When I order a drink and it can be delivered because of a shortage, I want to see a message to the coffee machine console that indicates me the shortage and that a notification has been sent

## Implementation details
You can take advantages of the 2 services implemented by the coffee machine:

`public interface EmailNotifier {
	void notifyMissingDrink(String drink)
}`

`public interface BeverageQuantityChecker {
	boolean isEmpty(String drink)
}`

Add those two services to your project and use mocking to finish your story.

##
[Previous iteration](iteration_05.md)