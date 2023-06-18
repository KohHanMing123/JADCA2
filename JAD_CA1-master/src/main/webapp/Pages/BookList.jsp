<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="models.SQLqueryBook, models.Book" %>
<%--
    String genre = request.getParameter("genre");

    SQLqueryBook query = new SQLqueryBook();
    List<Book> bookList = query.getBooksByGenre(genre);

    if (genre == null) {
        // Handle the case when no genre is selected
        // You can redirect to an appropriate page or display an error message
        // For example:
        response.sendRedirect(request.getContextPath() + "/Pages/error.jsp?message=No genre selected");
    } else {
        SQLqueryBook searchQuery = new SQLqueryBook();
        ArrayList<Book> books = searchQuery.searchBook("", genre, 0, 0, "", 9, 0);
        // Rest of your code to display the books based on the search results
    }
--%>

<%
    String genre = request.getParameter("genre");
    String search = request.getParameter("q");
    String minPriceStr = request.getParameter("mn");
    String maxPriceStr = request.getParameter("mx");
    String orderBy = request.getParameter("ob");
    double minPrice = 0, maxPrice = 0;

    System.out.println("genre got in booklist " + genre);
    if (minPriceStr != null) {
        try {
            minPrice = Double.parseDouble(minPriceStr);
        } catch (NumberFormatException e) {

        }
    }
    if (maxPriceStr != null) {
        try {
            maxPrice = Double.parseDouble(maxPriceStr);
        } catch (NumberFormatException e) {

        }
    }
    if (search == null) {
        search = "";
    }
    if (genre == null) {
        genre = "";
    }
    if (orderBy == null) {
        orderBy = "";
    }
    SQLqueryBook query = new SQLqueryBook();
    ArrayList<Book> books = query.searchBook(search, genre, minPrice, maxPrice, orderBy, 10, 0);
    // List<Book> bookList = query.getBooksByGenre(genre); // Commented out because it's not needed - previous version
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="../assets/logo.png" type="image/png">
    <title><%= genre %></title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="../js/twCustom.js"></script>
    <script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body class="bg-sand">
    <%@ include file="../components/navBar.html" %>


    <div class="flex justify-center my-10">
        <form action="<%=request.getContextPath()%>/SearchBookByGenre" method="post">
            <%System.out.println("genre to pass" + genre); %>
            <input type="hidden" name="genre" value="<%=genre%>">
            <div class="flex">
                <input type="text" class="text-xl py-1 rounded-l-lg w-96 pl-2" name="searchInput" value="<%=search%>">
                <button type="submit" class="flex items-center px-3 bg-white border-l rounded-r-lg">
                    <i class="fa-solid fa-magnifying-glass fa-xl"></i>
                </button>
                <button type="button" class="ml-4 py-1 bg-dark-blue text-white px-5 rounded-lg font-semibold text-lg duration-700 transition" onclick="toggleDropdownFilter()">
                    Filters
                    <i id="carrot" class="fa-sharp fa-solid fa-chevron-up"></i>
                </button>
            </div>

<div id="dropdown" class="hidden mt-3 duration-500 transition-transform mt-3 w-full border-gray-400 border-y-2 py-4 h-38">
    <div class="flex justify-between">
        <div class="flex flex-col">
            <div class="flex items-center">
                <p class="mr-3 font-semibold">Min Price:</p>
                <input name="minPrice" min="0" type="number" step="0.1" class="pl-2 rounded-lg py-1 w-20" value="<%=minPrice %>" >
            </div>
            <div class="flex mt-3 items-center">
                <p class="mr-3 font-semibold">Max Price:</p>
                <input name="maxPrice" min="0" type="number" step="0.1" class="pl-2 rounded-lg py-1 w-20" value="<%=maxPrice %>" >
            </div>
        </div>
        <div class="flex flex-col">
            <div class="flex items-center">
                <p class="mr-3 font-semibold">Order By:</p>
                <select name="orderBy" class="rounded-lg p-1">
                    <option value='default' class='text-grey'>None</option>
                    <% if(orderBy.equals("")){ %>
                    <option value="priceASC">Price - Ascending</option>
                    <option value="priceDSC">Price - Descending</option>
                    <% }else{ if(orderBy.equals("priceASC")){ %>
                    <option selected value="priceASC">Price - Ascending</option>
                    <option value="priceDSC">Price - Descending</option>
                    <% }else if(orderBy.equals("priceDSC")){ %>
                    <option value="priceASC">Price - Ascending</option>
                    <option selected value="priceDSC">Price - Descending</option>
                    <% } } %>
                </select>
            </div>
            <div class="flex justify-end mt-12">
                <button type="submit" class="py-1 px-3 bg-light-blue font-semibold rounded-lg">Apply Filters</button>
            </div>
        </div>
    </div>
</div>


        </form>
    </div>


    <div class="h-max mx-64 my-20">
        <div class="grid grid-cols-6 gap-12">
            <% if (books.isEmpty()) { %>
            <p class="text-4xl text-center font-bold col-span-6">No books found.</p>
            <% } else { %>
            <% for (Book book : books) { %>
            <div class="flex flex-col items-start space-y-4 mb-8 space-x-12">
                <div class="max-w-xs flex flex-col items-start">
                    <div class="h-56 w-40">
                        <img class="h-full object-contain flex hover:cursor-pointer hover:scale-105 duration-300"
                            src="data:image/jpeg;base64, <%=book.getImage()%>"
                            onclick="redirectToBook('<%=book.getID()%>')">
                    </div>
                    <div class="h-16">
                        <p class="text-sm font-bold mt-2 line-clamp-2"><%=book.getTitle() %></p>
                        <p class="text-xs mt-1 mb-3 line-clamp-1 font-semibold">
                            By <%=book.getAuthor() %>
                        </p>
                        <form action="<%= request.getContextPath() %>/addToCart" method="post">
                            <input type="hidden" name="book" value="<%=book.getID()%>"> 
                            <% System.out.println("HELLO THIS IS BOOKLIST" + book.getID()); %>                          
                            <input type="hidden" name="unitPrice" value="<%=book.getPrice()%>">
                            <button type="submit" class="mt-2 py-1 px-3 bg-dark-blue hover:bg-blue-600 rounded-full text-sm text-gray font-bold">
                                Add to Cart
                            </button>

                            <i class="fa-regular fa-star" onclick="toggleStar(this)"></i>
                        </form>


                    </div>
                </div>
            </div>
            <% } %>
            <% } %>
        </div>
    </div>

    <script>
        function redirectToBook(bookID) {
            window.location.href = 'Book.jsp?book=' + bookID;
        }

        function toggleStar(icon) {
            icon.classList.toggle('fa-regular');
            icon.classList.toggle('fa-solid');
        }

        function toggleDropdownFilter(){
            if(document.getElementById("carrot").classList.contains("fa-chevron-down")){
                document.getElementById("carrot").classList.replace("fa-chevron-down", "fa-chevron-up")
              /*   document.getElementById("dropdown").classList.add("-translate-y-56") */
                document.getElementById("dropdown").classList.remove("h-44")
               /*  setTimeout(() => { */
                    document.getElementById("dropdown").classList.add("hidden")
               /*  }, 
                500); */
            }else{
                document.getElementById("carrot").classList.replace("fa-chevron-up", "fa-chevron-down")
             /*    document.getElementById("dropdown").classList.remove("-translate-y-56") */
                document.getElementById("dropdown").classList.add("h-44")
                document.getElementById("dropdown").classList.remove("hidden")
            }
        }
    </script>
</body>


</html>
