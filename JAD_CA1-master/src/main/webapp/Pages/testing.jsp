<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body class="min-h-screen">
<%@ page import ="books.*, java.util.*" %>

<%-- TESTING SEARCH --%>
	<%
	String search = request.getParameter("q");
	String genre = request.getParameter("g");
	String minPriceStr = request.getParameter("mn");
	String maxPriceStr = request.getParameter("mx");
	String orderBy = request.getParameter("ob");
	double minPrice = 0, maxPrice = 0;
	if(minPriceStr != null){
		try{
			minPrice = Double.parseDouble(minPriceStr);
		}catch(NumberFormatException e){
			
		}
	}
	if(maxPriceStr != null){
		try{
			maxPrice = Double.parseDouble(maxPriceStr);
		}catch(NumberFormatException e){
			
		}
	}
	if(search == null){
		search = "";
	}
	if(genre == null){
		genre = "";
	}
	if(orderBy == null){
		orderBy = "";
	}
	SQLqueryBook query = new SQLqueryBook();
	ArrayList<Book> books = query.searchBook(search, genre, minPrice, maxPrice, orderBy, 9, 0);
	ArrayList<String> genres = query.getAllGenre();

	%>
	<div class="bg-sand min-h-screen flex flex-col">
		<%@ include file = "../components/navBar.html" %>
		 <div class="flex justify-center my-10">
	        <form action="<%=request.getContextPath()%>/SearchBook" method="post">
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
	            
	            	<div id="dropdown" class="hidden mt-3 duration-500 transition-transform mt-3 w-full border-gray-400 border-y-2 py-4">
		            	<div class="flex">
			            	<div class="flex">
			            		<p class="mr-3 font-semibold">Genre:</p>	
			            		<select name="genres" class="rounded-lg p-1">
									<%
										if(genre.equals("")){
											out.print("<option selected value='default' class='text-grey'>All</option>");
										}else{
											out.print("<option value='default' class='text-grey'>All</option>");
										}
									%>
									
								    <%for(int i = 0; i < genres.size(); i++){
								   		if(genres.get(i).equals(genre)){
								   	%>
								   	 	<option selected value="<%=genres.get(i) %>"><%=genres.get(i) %></option>
								   	<%
								   		}else{
								   	%>
								   		<option value="<%=genres.get(i) %>"><%=genres.get(i) %></option>
								   	<%
								   		}
								    } 
								    %>
								</select>
			            	</div>
		            		<div class="flex grow justify-end">
			            		<p class="mr-3 font-semibold">Order By:</p>	
			            		<select name="orderBy" class="rounded-lg p-1">
									<option value='default' class='text-grey'>None</option>
									<%if(orderBy.equals("")){ %>
								    <option value="priceASC">Price - Ascending</option>
								    <option value="priceDSC">Price - Descending</option>
								    <%
								    }else{ 
								    	if(orderBy.equals("priceASC")){
								    %>
								    <option selected value="priceASC">Price - Ascending</option>
								    <option value="priceDSC">Price - Descending</option>
								    <%}else if(orderBy.equals("priceDSC")){ %>
								     <option value="priceASC">Price - Ascending</option>
								    <option selected value="priceDSC">Price - Descending</option>
								    <%}} %>
								</select>
			            	</div>
		            	</div>
		            	<div class="flex">
		            		<div class="flex flex-col">
			            		<div class="flex mt-3 items-center">
			            			<p class="mr-3 font-semibold">Min Price:</p>
			            			<input name="minPrice" min="0" type="number" step="0.1" class="pl-2 rounded-lg py-1 w-20" value="<%=minPrice %>" >
			            		</div>
			            		<div class="flex mt-3 items-center">
			            			<p class="mr-3 font-semibold">Max Price:</p>
			            			<input name="maxPrice" min="0" type="number" step="0.1" class="pl-2 rounded-lg py-1 w-20" value="<%=maxPrice %>" >
			            		</div>
			            	</div>
							<div class="grow flex flex-col justify-end items-end">
								<button type="submit" class="py-1 px-3 bg-light-blue font-semibold rounded-lg">Apply Filters</button>
							</div>
		            	</div>
		            	
					</div>

	        </form>
	        </div>
	    </div>
</body>
</html>