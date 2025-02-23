package demoqa.project.api.steps;

import demoqa.project.api.actions.BooksActions;
import demoqa.project.api.actions.CommonActions;
import demoqa.project.api.actions.DeleteBookActions;
import demoqa.project.api.actions.UserActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddBookSteps {
    BooksActions booksActions = new BooksActions();
    UserActions userActions = new UserActions();
    CommonActions commonActions = new CommonActions();
    DeleteBookActions deleteBookActions = new DeleteBookActions();


    @Given("the user has retrieved the list of available books")
    public void retrieveBooksList() {
        booksActions.getAllBooks();
    }

    @When("the user adds a book to their profile")
    public void addBookToProfile() {
        booksActions.addBook(1);
    }

    @When("the added book is verified in the user profile")
    public void verifyBookIsVerified() {
        commonActions.getUser();
    }

    @Then("the book is removed from the user's profile")
    public void removeBookFromProfile() {
        deleteBookActions.deleteBook();
        commonActions.getUser();
    }

    @Given("the user adds {int} books to their profile")
    public void addMultipleBooksToProfile(int numberOfBooks) {
        booksActions.getAllBooks();
        booksActions.addMultipleBooks(numberOfBooks);
    }

    @Then("all books are removed from the user's profile")
    public void removeAllBooksFromProfile() {
        deleteBookActions.deleteAllBooks();
    }

    @And("the user verifies that no books remain in their profile")
    public void verifyNoBooksExists() {
        userActions.verifyUserHasNoBooks();
    }
}
