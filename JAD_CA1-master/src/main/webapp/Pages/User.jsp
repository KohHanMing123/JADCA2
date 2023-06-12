<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body class="bg-sand">
  <div class="container mx-auto py-8">
    <h1 class="text-3xl mb-6">Update Profile</h1>
    <div class="bg-white p-8 rounded shadow">
      <form>
        <div class="mb-4">
          <label for="username" class="block font-bold mb-1">Username:</label>
          <input type="text" id="username" name="username" class="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:border-blue-500" required>
          <i class="fa-solid fa-pen-to-square"></i>
        </div>
        <div class="mb-4">
          <label for="password" class="block font-bold mb-1">Password:</label>
          <input type="password" id="password" name="password" class="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:border-blue-500" required>
          <i class="fa-solid fa-pen-to-square"></i>
        </div>
        <div class="mb-4">
          <label for="email" class="block font-bold mb-1">Email:</label>
          <input type="email" id="email" name="email" class="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:border-blue-500" required>
          <i class="fa-solid fa-pen-to-square"></i>
        </div>
        <div class="mb-4">
          <label for="image" class="block font-bold mb-1">Image:</label>
          <input type="file" id="image" name="image" class="w-full">
        </div>
        <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">Update</button>
      </form>
    </div>
  </div>
  
  <script>
    function toggleUsernameInput() {
      const usernameText = document.getElementById('usernameText');
      const usernameInput = document.getElementById('usernameInput');
      usernameText.classList.toggle('hidden');
      usernameInput.classList.toggle('opacity-0');
      setTimeout(() => {
        usernameInput.focus();
        usernameInput.classList.toggle('opacity-0');
      }, 50);
    }
  </script>
  
</body>
</html>