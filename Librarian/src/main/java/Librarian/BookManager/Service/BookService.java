package Librarian.BookManager.Service;

import Librarian.BookManager.Entity.Book;

import java.util.List;

public interface BookService {

    public void deleteBookById(int theID);

    public List<Book> getBooks();

    void saveBook(Book book);

    Book getBookbyID(int theID);
    /*
    Question

Why do we have to create Service layer what has the same functions as DAO layer?
Is it necessary to create all this layers?

Answer

Agreed, there are a lot of layers. However this is the architecture that you will
see on real world, complex Spring projects. In our example, it is fairly simple.
We simply delegate the calls to the DAO. So I agree, you could remove the service
layer in this simple example and have controller call dao directly.

However, we added the service layer to leverage the Service Layer design pattern.
On a much more complex project, we could use the service layer to integrate
multiple data sources (daos) and perform transaction management between the two.
So, for a simple project that we have here ... this probably overkill. However,
I wanted to show you design patterns that you will encounter on real projects.
     */
}
