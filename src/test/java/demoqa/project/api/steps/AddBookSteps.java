package demoqa.project.api.steps;

import demoqa.project.api.actions.BooksActions;
import demoqa.project.api.actions.DeleteBookActions;
import demoqa.project.api.actions.GetUserActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddBookSteps {
    BooksActions booksActions = new BooksActions();
    GetUserActions getUserActions = new GetUserActions();
    DeleteBookActions deleteBookActions = new DeleteBookActions();


    @Given("the user has retrieved the list of available books")
    public void retrieveBooksList() {
        booksActions.getAllBooks();
    }

    @When("the user adds a book to their profile")
    public void addBookToProfile() {
        booksActions.addBook(1);
    }

    @And("the added book is verified in the user profile")
    public void verifyBookIsVerified() {
        getUserActions.getUser();
    }

    @Then("the book is removed from the user's profile")
    public void removeBookFromProfile() {
        deleteBookActions.deleteBook();
        getUserActions.getUser();
    }
}
