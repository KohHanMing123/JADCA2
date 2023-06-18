package admin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

import javax.servlet.http.Part;

import books.Book;
import books.SQLqueryBook;
import user.User;

public class SQLqueryAdmin {
	private String username = System.getenv("PLANETSCALE_USERNAME");
	private String password = System.getenv("PLANETSCALE_KEY");
	
	public int adminLogin(String adminPassword, String adminUsername) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Admin WHERE password = ? AND username = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, adminPassword);
		    ps.setString(2, adminUsername);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	return rs.getInt("adminID");
		    }
		    throw new Exception("Invalid");
		}catch (Exception e) {
	        throw e;
	    }
	}
	
	public boolean verifyAdmin(int adminID, String adminUsername) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Admin WHERE adminID = ? AND username = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, adminID);
		    ps.setString(2, adminUsername);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	return true;
		    }
		    return false;
		}catch (Exception e) {
	        throw e;
	    }
	}
	
	public ArrayList<Book> getAllBooks(int limit, int offset) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Books ORDER BY dateAdded DESC LIMIT ? OFFSET ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, limit);
		    ps.setInt(2, offset);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<Book> books = new ArrayList<Book>();
		    while(rs.next()) {
		    	int id = rs.getInt("id");
		    	String title = rs.getString("title");
		    	String publicdate = rs.getString("publication_date");
		    	String author = rs.getString("author");
		    	String genre = rs.getString("genre");
		    	double price = rs.getDouble("price");
		    	String isbn = rs.getString("isbn13");
		    	String dateAdded = rs.getString("dateAdded");
		    	String description = rs.getString("description");
		    	int stock = rs.getInt("stock");
		    	Blob imageBlob = rs.getBlob("imageBLOB");
		    	String base64Image = "/9j/4AAQSkZJRgABAQAAAQABAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZyBJSkcgSlBFRyB2NjIpLCBxdWFsaXR5ID0gNzUK/9sAQwAIBgYHBgUIBwcHCQkICgwUDQwLCwwZEhMPFB0aHx4dGhwcICQuJyAiLCMcHCg3KSwwMTQ0NB8nOT04MjwuMzQy/9sAQwEJCQkMCwwYDQ0YMiEcITIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIy/8AAEQgCGgFcAwEiAAIRAQMRAf/EAB8AAAEFAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQAAIBAwMCBAMFBQQEAAABfQECAwAEEQUSITFBBhNRYQcicRQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYnKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6g4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh4uPk5ebn6Onq8fLz9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYHCAkKC//EALURAAIBAgQEAwQHBQQEAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRCkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm5+jp6vLz9PX29/j5+v/aAAwDAQACEQMRAD8A9+oopaAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEopaKAEoxS0lABS0lLQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABSUtJQAUtJS0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUlLSUAFLSUtABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFJS0lABS0lLQAUUUUAJXLat8QvC2h6lNp2pap5N3FjzI/IlbGQCOVUjoa6mvmP4s/8lM1b/tj/AOiUqoq7Jk7I+hdC8SaT4ltpLjSLsXMUbbHOxlIOO4YA1sV8t/Dzxa3hPxNHNIW+wXOIrpf7q54b/gJ/TNfUCSLKiyRsGRgCrDkEGlJWCLujH8QeLNE8L/Z/7YvfswuN3lfunfdtxn7oOMbhSaD4v0PxM88ej3v2loADIPKdNuen3gPSvM/j7/zL3/bx/wC0qh+An/H9rn/XOL+bVXL7txc3vWPb6KKKgsKKKKACiiigArmdZ8e+GvD2oGw1TUvs9yEDFPIkbg9OVUiumrxv4jfDjxB4l8WPqWnJAbcwooLyhTkCmrdSXfoen6Hr+m+IrE3ulXP2i2DlC+xl+YdRhgD3rUrjfhr4cv8Awv4XfT9SWNZzcvLiNtw2kKP6V2VDKWwtFFFIAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKSlpKAClpKWgAooooAK+Yviz/yU3Vv+2P/AKJSvp2vmL4s/wDJTdY/7Y/+iUq6e5E9jlJ7K4trW0uZYmEN0GaJ+x2sVP4givcfg34w/tHTD4fvZc3VmubcluXi9Pqv8vpWfoHhJPF3wTtrZVX7bBJNLas398O3y/Q9K8l0rUb3w7rsN9Buiu7STO1uORwyke/Q1o/fTRmvdsz1j4+/8y9/28f+0qh+An/H9rn/AFzi/m1U/i9rdr4h0Twpqlo37qdLg47o37vKn3B4q58BP+P7XP8ArnF/Nqj7A/tnsGratZ6Jpk+oX8oit4V3O39B6mvA/Evxh1/Vbh49Lf8As2z6KI8GQj1Ldvwrf+O2sSCXTNEjfEZQ3Mq+pztT+T1m/BrwnZ6ve3mr6hAs8VmyxwRuuVMh5JI74GPzoSSV2U227I4xfGPiyAib+3tWG7lS9y5U/gTivQPBfxkuluo7HxNtlhchReooVkP+2BwR7j9a9mvNPs9Qs3tLy2imt3GGjkUEYr5b8b+H08MeLr3TIdxgQh4c9djAEDPtnFNNS0sS0463PqtHWRAysCrDII6EU8VwXwh1mXVvAsMc77pLKVrbJ67QAV/INj8K72s2rOxqndC14d8T/GviLQvGcllpupyW1sIEYIEUjJHPUGvca+b/AIzf8lBm/wCveL+VVDcmex6t8Kta1HX/AAi95qd01zOLp4w5UD5QFx0x61wXxK8b+JND8b3ljpuqSQWyJGVjCIQCUBPUGuv+CX/Ihv8A9fsn/oK15f8AF/8A5KPf/wDXOL/0WKaXvMTfuo1Z/i3r91oNhpeltJJqjIRc3XlBpHYseEUDHTHOK5eXxd4z0y/zc6zq0NwPm8u4lfHP+w3H6V6p8ENFt4vDlzq7RA3U85jVzgkRqBwPTknNQfHe0jOjaVebF85Lhow3faVz/wCy001e1iWna9zMt/jfcJ4WKzWqya4DsV9uIiv/AD0I9f8AZFcVqPizxzJsv7zU9XgikOUdGeGI/TbhTV34T6Jba345hS6iWWG1ha4KOuQSCFGfoWBr3vxjZw33g3WoJkVlNnKwyOjBSVP4EZodk7WKV2r3PLPhv8UNSm1m30XXZvtMdywjguW++rnoCf4gTx65r3Cvj7Q3aLXtNdGw63UZB9w4r68liE0DxNkK6lSQcHBHb0pVEkxwbaPDPHPxb1KTU59P8PTrbWkLlGuVAZ5SODgnoPTHNce2u+Omt/t39peIPIPzeaJZhHj6/dr1m8+FHgnSp4b+6vZrSGOQMVuLlBG+Dnadwzg49a1dT+K3g7TYzGL1rwgY8u1iLDH1OF/WhNdEJp9WeV+GPi3r+k30SapctqFiSA6y4Mij1VuufY19ExSpcQpNEwaORQyMO4IyK+PL+WGfULmW2i8qF5maNP7ikkgfgK+rvCsn/FEaJLI3/MOgZif+ua0TWwQe5w/xI+J0nhy4bR9HWNtQxmWZwCsGRkADu2OeeK8pi8VeNdVu2e21bWJ5hyUtpZMD/gC8fpWFqmoS6pq11fzHMtzK0jZ9Sc4/Cvqjwn4etfDXh61sLaJVcRgzPjmSQj5ifxpu0VsSrye543ZfFvX7TSNR0vVmk+3iEi2uvKCyRyDs64wfrio/Bfj/AMU6p4y0uyvdYklt5pwkiFEAIx7Cu6+Mnhy1v/CcusCJReWJU+YF5aMtgqfzzXkHw7/5KFon/XwP5GhWabsDumlc+ory8gsLKa7upVighQySO3QKBya+f/FPxf1vVLqSHRpm0+wHClFHmuPUt/D+Fd38btUks/CNtYxNtN7cAP7og3EfntrzL4XLocfiv7XrtzbQw2sJeH7QwCmXIA69cDJpRWl2VJ62KX/CS+ONNVL2XVNaijf7jzvIYz9N3ymvTfh38Vpdbvo9H13yxdycQXKAKJD/AHWHQE9sV2F94s8GajZTWl5relzQSoVdGnUgg18zXgXTdbuBY3O5La5YQTxt1CsdrA/hmmve6Ev3ep9iUVQ0e+/tPRLC/wCn2q3jmx/vKD/Wr9ZGwUUUUAFJS0lABS0lLQAUUUUAFfMXxZ/5Kbq3/bH/ANEpX07XzF8Wf+Sm6x/2x/8ARKVcNyJ7HsPwf/5JxYf9dJf/AEYa4H4zeD/sGoDxFZJi2um23QH8EvZv+Bfz+td98H/+ScWH/XSX/wBGGut1bS7bWtKudOvI98FwhRx/Ue4PIovaQWvE+Q3uZ3tYrZ3YwxuzonYM20N+e1a9c+An/H9rn/XOL+bV5j4h0O68Oa5c6Xdr88L4VuzqejD6ivTvgJ/x/a7/ANc4v5vWkvhM4fEUvjtaOnibTbw/6uSy8pfqjsT/AOhitv4EX8J03VtO3KJlmWcL3Klcf+y12fxB8IDxh4eNvEyrewN5ls56bscqfYivnWKXXPBmvbl8/T9RtzggrjcPx4ZT+RqFrGxb0lc+uK+Y/izfw3/xCv8AySrLCqQFhz8yqM/kTirl98ZPFd9Ym1U2lqzDBmgiIkx9SSB+ArA8K+EdU8Y6oIbVGEO/NxdupKxg9ee7H0pxjbVkylfRHsPwQtJIPBdxcSKQLm8Zk91CqufzBr02qGj6XbaJpNrptou2C2jEaZ6nHUn3J5rQrNu7NErKwV83/Gb/AJKDN/17xfyr6Qr5v+M3/JQZv+veL+VVDcU9j0j4Jf8AIhv/ANfsn/oK15h8X/8Ako9//wBc4v8A0WK9P+CX/Ihv/wBfsn/oK15h8X/+Sj3/AP1zi/8ARYpr42S/hR6r8Gf+Sew/9fEv8xWZ8dv+RW03/r9/9katP4M/8k9h/wCviX+YrM+O3/Irab/1+/8AsjUl8ZT+E5L4Gf8AI7Xn/YOf/wBGx17d4k/5FbVv+vKb/wBANeI/Az/kdrz/ALBz/wDo2OvbvEn/ACK2rf8AXlN/6AaJfEEfhPk/R/8AkN2H/XzH/wChCvq7xHrCaD4dv9TYK32aEuqnoW/hH4mvlHR/+Q3Yf9fMf/oQr6p8V6Q2v+FtR0xCBJcQkRk8DeOV/UU57oUNmfMZl1jxt4mhjmuWnvryYIhkb5Uyf0Uegr2TSPgfoVsqPqd3dXso+8qERx/kPm/WvEYZdQ8N65HKI2t7+ymDbZF5DKehFei6j8btWv8ATPsljpkNpeSDYZxKXOT/AHVwMH8TVNPoJNdTzfWYoLfXdQgtlxbx3Mixgc4UMQP0r6l8Kp5ngLREzjdpkAz9Ylr5Y1LS73Sbv7LqFvJBclFfZJ1wwyK+nfh3qEWo+AdHkjbPlW6wMO4KfL/SlU2Q4bs+W54ntriWGVcSRuVYehBwa+vdH1KHWNHs9Qt2Vo7iJZBj3HI/PivEfiz4CurLVbjxDpsDS2VyTJcKi5MUh6sf9k9c9jXGaB428Q+GYXh0u/aKBjkxOiumfUAjj8KGuZaEp8r1Pc/i9qkNh4CurZ3XzrxkiiU9ThgzH8AK8S+HX/JQtE/6+B/I1avNP8TeLNJv/FGsTzNa2kI2SyrgOSwG1FGBjnJIGKq/Dv8A5KDon/XwP5GhKyYN3aPTvjzbu2jaPcgfJHcPGfqygj/0E15v4C8I2/jLVrmwmv2tGjh85CsYbfhgD3HrX0L4v8OReKfDd1pcjKjuu6Fz/BIOVP07H2r5n/4nfgjxGGZZLLUbV8jK8N/RlI/OiL0sgktbnqv/AAoO2/6GCb/wGH/xVV4PgnpNySLfxUJSOCI4Ubp9GrEv/jZr95pb2sVraW9w6bWuI924Z7qCeDXOeCfB174u1mKKNJFsEcG5uOyL6A/3j2o97qx+70R9MaHpo0bQ7LTBK0otYViDkYLYGM1o1HFGsMSRxjCIAqj0AqSsjUKKKKACkpaSgApaSloAKKKKACsm68N6FfXT3N5o2n3M743SzWqOxwMDJIz0Fa1FAFWzsbTTrZbaytYbaBckRwoEUZ9hxVqikoAzb7QNH1SYTahpNldyhdoe4t0kIHpkg0/T9G0rSi507TbSzL4Dm3gWPdj1wBmtCigArP1HR9O1iHytSsLe7jHQTRhsZ9M9K0KKAOUj+G/g+OXzV0C13dcHcR+ROK6O1tLeyt1t7WCOCFfuxxIFUfQDirFFFwsFFFFABWXe+HtE1K4+0X2j2F1NjHmT2yO2PqQTWpRQBTsdOsdMg+z6fZ29pCTu8uCIIuT3wMCq154c0PULg3N7o2nXM7YBkntkdjj3IzWrRQBVstPs9Ng+z2NpBawg58uCMIuT7DimX+l6fqsSxahY212incq3ESyAH1wc1dooAzLHQNH0ycz2GlWVpMw2l7e3RGK56EqBxxV6WKOeF4pUV43BVkZcgg9QR3FS0UAYq+EfDccgkj8P6UjqQVZbOMEEe+K2cUtFAGNq3hbQ9dIbU9LtrmQDAd1w+P8AeHNRaV4O8O6LMJtO0i1hmHSXbucfRjkit6ii4WMy+8P6NqdwJ7/SbC7mC7Q9xbI7Y9MkHip7HTLHS4DDp9lbWkRO4pBEsalvXAq5RQB4xqPxo1HR9cvtOutDik+zXDxZErIWAbAPQ9RW/wCE/EHgvxdA15caPo9lqCPhop0iL/7wYqCRS+O/hZbeK7ltSsrgWepEASblzHNgcbscg+/6V5rcfBnxfC+I4LScf3o5wB/49g1p7rRl7yZ6F8VPFmjw+DbrSbW9tp7u62IkUMgbYoYEk7enArzL4T6e998Q9PdVzHbB55D6AKQP/HitaFl8FfFVzIBc/YrVe7SS7j+S5r1/wV4EsPBlk627tPdzY864dcFsdgOwoukrJjs27tHW1Q1PR9O1iHydRsbe7jHQSxhsfTPSr9FZmhyafDbwfHN5q6BbFuuCWI/InFdJa2lvZW6QWsEcEK8LHEgVR9AOKsZozRcLBRRRQAUUUUAFJS0lABS0lLQAUUUUAFFFFACV87+JPiz4kbxHfjR9U8nT1mKQKIIm+UcZyyk84zXrvxG14eHvBN/co+y4mX7PAe+9wRn8Bk/hXzXoGiXXiHWbbS7Pb505IBPQAAkn8hWlNLVszm+iPWfhf8RtY1vxM+l65fLcieEm3PlIhDryR8oHUZ6+lezV8eade3GiazbXkW5bi0nDgNx8ynof5V9c6bfw6np1tfW7ZhuY1kQ+xGaU1YcHdFqisDxF4x0XwsbcavPJD5+7yysTODjGeg96j8P+N9B8UXMtvpV20ssSb3Vo2Q7c4zyOamzLujpKKK5XXPiF4b8O6k2n6leNHcqgZlSJnwD06CkB1VFY3h7xNpnii0ku9KleWGN/LZmiZPmABxyB61F4g8W6L4YiV9Vvo4XcZSIZZ3+ijn8aLBc3qK8yf44+F1fatrqrj+8Io8fq+a6Dw/8AEXw34juVtbK+ZLpvuw3C7Gf6difYGnyvsK67nW14R8QviF4p0LxzqWm6bqnk2kXl+XH5ETYzErHllJ6mvd68z8U6p8N7bxHdxa/axyaouzzma3dicou3kcfdxRHfYUttze+HGs32u+C7XUNSn8+6keQNJsVc4cgcKAK4X4peOfEfhzxYllpWo/Z7c2qSFPIjb5iW7spPavSvCdxol14egl8PIsemEt5aqhUZDHdweeteJ/G3/kfE/wCvKP8A9Caqj8QpfDuem/CjxBqniXwvc3ur3X2idLxolbYqYUIhxhQB1Y13teYfAv8A5Em8/wCwi/8A6Kjr0a9vbXTrSS7vbiOC3jGXkkYBQPrUvccdizRXnF58avClrMY4vt92B/HBAAP/AB8qafp/xm8J3syxyPd2e443XEQx+alqOV9g5keiV598WfEmreG9BsrnSLr7NNJdeWzbEfK7GPRgR1Fd1b3EN1bx3FvKksMgDI6MCrA9wa8x+O3/ACK2m/8AX7/7I1Ed0Etih8KfG3iLxL4pubLV9R+026WTTKvkRp8wdBnKqD0Y17JXzx8E54bXxdqE9xKkcSabIzuxAUASR8k16Df/ABn8KWVyYY/tt2AcGS3iG3/x4gmqktdEKL01Z6NRXMeG/HegeJz5Wn3n+khcm3lXZJgeg7/hXT1BZ8/eNPiP4s0nxjqdhY6t5VtBKVjT7NEcDHqVJr1zwHqV3rPgrTNRv5fOupkYyPtC5Idh0AA7V88/EX/koWt/9fB/kK98+GH/ACTjRv8Arm3/AKMatJJWRnFu7OvpKr3l9a6daSXV5cRwW8Yy8kjAKB9a4G8+NXhS1nMcQv7tR/y0ggAX/wAfKn9KhJsttLc9GxRXH6B8TPDPiKdLa2vGgunOFhuV2Fj6A8gn2zXYUrWHcWiiigAooooAKSlpKAClpKWgAooooAKKKrXt3DYWM95cPthgjaSRvRQMmgDw3436/wDa9etdEif93ZJ5soH/AD0ccfkuP++qufAvQ99zqGuypxGPs0J/2jhn/Tb+deVaxqc2s6xealcf625maUj0yeB+A4r0Dwn8WofCvh620qHw/wCd5W4vL9s2mRick42Gtmny2Rimua7Mj4qaF/Yvjm7eNcQXo+0x+mWPzD/vrNel/BTXf7Q8MzaVK+Z7CT5Af+eTcj9d36V5t48+IEXje3s1OjfY57V2Ky/afMypHK42juB3qv8ADPXv7A8b2ckkmy2uv9Gmz0wx4P4NtoabjqCaUtD2n4q+H/7c8E3LxpuubH/SYsdcAfMPxXNeH/DrXf7A8bafcs+2CV/s8/YbH4yfocH/AIDX1Kyq6lGGQRgg9xXyd4y0JvDniy/00BhEkm6E+sbcr+Q4qaeqaKno0z6vklSCF5ZGVY0BZmPAAA618j+ItVl1/wAS3+pFWzdTlkXuFzhR+AwK9h13xv8AaPgnDe+b/p18gsXO7neMiQ/iqsf+BV578LtA/t3xxaeYmbez/wBKl9PlI2j/AL6xRFWuwk72R734O0MeGfCNlp+zMyR75gOrSHlv14rwTxD4d8Za54imvb/RL/zrqfCnymZIwThV3DIAHSvptmCqSTgDkk+leReJvjdDZ3ctroNlHdbCV+1TsdhI/uqMEj3yKUG76IckrahH8CLD7Cok1q5+145ZEXy8/wC71x+NeNXttcaPq1xau+25s52iLxtjDoxGR+IrvV8W/Evxbkact2IW4zZweWg/7adv++q8+1CC6ttTure+3fa45nSfe247wxDZPfnvWiv1Zm7dEfWfhu+fU/DOlX8pzLcWscjn/aKgn9a+ePiz/wAlN1b/ALY/+iUr3zwL/wAiJof/AF5R/wDoIrwP4s/8lN1b/tj/AOiUqIfEy5/Cew/CD/knNh/10l/9GGvMfjb/AMj4n/XlH/6E1enfCD/knNh/10l/9GGvMfjb/wAj4n/XlH/6E1EfiE/hR3fwL/5Em+/7CL/+io6yvjBZ+JtZvbay0/S7yfTIE3sYELiSQ+w54HStX4F/8iTff9hF/wD0VHWv42+I+n+DylsYmu9QcbhAjbQq+rHnFL7TsV9lHnng34OtrOl/btcmurEu5EdsI9sgAOMtu6Z9MVzHxB8E/wDCF6nbQxXTXFtcxlo2dcMCDyD61uS/Ffxvr0xt9It0ic9Es7Uyvj8d36CuW8V2niiKa2ufE/2vzZwxh+0yZOBjOFz8vX0rRXvqzN2toj1T4FalNcaNqmnyOzJaSo8YPO0OG4Htlc1N8dv+RX03/r9/9kasv4Bf8zD/ANu//tWtT47f8itp3/X7/wCyNUfbL+weLaFpuoa3q0ek6azedefu2G4hSoIY7vYY3V64fgPZ/wBnbV1mf7dj7xiHlbv93rj8a5r4Hxxv45nLhSY7GRk9jvQfyJr6HxROTT0CMU1qfIN5a6l4X8QSQSM1vqFlJwyNggjkEH0I5r6d8F6//wAJN4VsdTIUTSJtmA7SKcN/LNeJ/GmJY/HxdeslrGzfXlf5CvQPgfIz+B7lWPCag4X6bIz/ADNEtYphHSTR5D8Rf+Sha3/18H+Qr3z4Yf8AJN9G/wCub/8Aoxq8D+Iv/JQtb/6+D/IV758MP+Sb6N/1zf8A9GNRL4UEd2eN/FbxdNrviWbT4pWGnWDmNEDcPIOGc+vPA9q2vB/wcXWtFh1HV76a2FwgeGGBRuCnkFic9fTFeVzyvcXEs0jfvHcsx9yc16JbfFrxjbWsNvFY2nlxIEX/AEVzwBgd6tp2siU03dmH468EXPgrUYUM/n2dxkwT7cHIxkMPUV7B8JPFc/iLw5Ja3srSXtgwRnbkvGR8rE9zwRXkXinxl4h8YWkFtqVnCEgk8xDFAynOMdya6f4G/aYPFOoQyRSJFJZFjlSBlXTH6MaTXu6gn72h73RRRWJsFFFFABSUtJQAUtJS0AFFFFABXmfxn14aZ4TTTo3xNqMm0gdfLXlv12ivTK8n+IHw68R+MPEf222udOjs4o1ihSWWQNjqSQEI6k96cbX1Jle2h5R4J8Mnxb4ng0xnkSEq0k8iYyiAf1JAr1n/AIUVoX/QU1H/AMc/+JrS+Gnw/uvBpv59RltprufaiG3ZiFQckcgdT/KvQ6uU3fRijFW1R5V/wonQv+gpqP8A45/8TXh2o2U+larc2M/yzW0zRN2+YHGa+x68h8e/CjUvEniiXVdJnsokmjXzluJHU+YBjI2qeMBaIy7sUo9kd14H10eJPCOn6gzZmMflzf8AXReG/PGa4H45eH/Ms7LXoU+eFvs85H908qfwOR/wKui+GnhDXPB0F7aalcWU1rMyyRC3kdijjhs7lHUY/Kur8QaPFrugXuly423MRQE9m/hP4HmpvaWg7XjqfJr6hcvpkOnF/wDRYpmmVf8AaYKCfyUV738F9A/szwq+pypi41F9w9fLXIX8zuP41xUfwL8R+YBJf6UI8jcVkkJx/wB8V7zZWkNhZQWlumyGGNY0X0UDAqpSVrImMXe7MXxy88fgbW3ttwlFpJyOoG3n9M18w6C9jF4h059SVTYi4jNwG5HlhhnPtivryWKOeJ4pUDxuCrKeQQa8M8S/BPUY72Wbw/LFPauSVgmfY6Z7A9CPrRTa1THNPdHsF7rmkaTo41C4vbaKwVMo6sNrADgLjr7Yr5S1i/Gp63f6jt2/ariSfae29i1d9pXwT8R3dwBqMttY2+fmO/zHx7Acfma2Nf8AghdvfRf2BdWkdoIlVxdyvvZxnLcKRzTVl1Jd30PSvATB/AehkHOLOMfiBXg3xZ/5KZrH/bH/ANEpXufgLRNW8OeGI9K1WW2laB28l7d2YbCc4OVHOSa4Xxt8Kdd8S+L7/V7K606OC48vas0jhvlRVOcIR1WlFpSZUk3FHUfCD/knFh/10l/9GNXmPxt/5HxP+vKP/wBCavY/Afh678L+E7bSr54ZJ42cs0LEryxPcA964/4ifDTWvF3iZNSsLiwihW3WLE7uGyC3opHekmuYbT5bFj4F/wDIk3n/AGEX/wDRUdeS/EWSeX4g6ybj74uCq5/uAAL+mK92+G/hS/8AB/h640/UJLeWWS7acNbsxXaURe4HPy1kfEL4YL4quP7T06eO31IKFcSfclA6ZI5BA4zTTXMJp8qLXwnudI/4QW2SxaJZ49xvBkBw+Tkt36dPavNfjD4ksdc8Q2ltp86zxWUbK8qNlS7HkAjrgAVBH8G/F7z+Wbe1jX/nobkbf0yf0rqZfgd5fhl4or2GXWWkVhLJuWFFHVRgE/jj8qeid7hq1awz4BsA+vp3ItyB9PM/xrV+O3/Irad/1+/+yNR8PPh54j8G+IHurm606WzmiMcyQyyFvVSAUA6+9b/xJ8Iah4x0a0s9PltopIrjzWNwzAY2kdgfWpuua47PlseEeBfEi+FfFlrqUqs1vzFOE67G7/gcGvpIeLvDx03+0BrVkbXG7f5y/ljrn2615p4a+DFxbtfxeIJbKa3uINkTWzuZIZAwIYblHaud1L4K+JrW5YWLW17Dn5HDhGx7hu/4mqfK3uJcyWxzPjnxCvifxZe6nErCBiI4c8HYowD+PWve/hbo8mjeA7KOdWWa5LXLqe24/L/46FrjPB/wXe2vY77xHLFKIyGS1iYsCR/fJHT2FeygbeB0pSatZBFO92fK3xF/5KFrf/Xwf5CvfPhj/wAk30b/AK5t/wCjGrgvFXwi8Qa54p1DU7e801IbmUuiySyBgMd8IRXpvg7Rrnw94TsNJvHhee2Qq7RElTlieMgHv6USasgindnzT4v0aTQPFepafIrKiTM0RPeMnKn8jXvvgfx3pGvaBarNfQQX8UYSeGaQKxYDBYZ6g9eKseNfAOn+M7VDK7W19EMRXKDOB6EfxCvIr34MeK7aYrbraXadnjnC/o2KLqS1YWaeiO78ZfF220LVIbLSI7bUcAm4cSHap7KGHGfWt7wD42uPGtrd3L6T9iigdUEnn7xIxGSB8oxgY/OvM9G+CGt3NwjavdQWduDlljbzJD9McD65r27RdHs9B0qDTrCLyraEYUdye5PqSaT5baDXNfU0qKKKgsKKKKACkpaSgApaSloAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApKWkoAKWkpaACiiigAooooAKKKKACkpaKACiiigAooooAKKKKAEopaKACiiigBKKWigBKKWigBKKWigBKKKKAFpKWigBKKWigBKKWigBKKWigBKKWigBKKWigAooooAKSlpKAClpKWgAooqC6uYLO2kuLmVIYY1LPJIwCoB3JoAnorzXXPjP4d03dHp6TalMOhj+SPP8AvHn8ga861n4x+JtS3JZmHToTniBdz492bP5gCqUGyHJI+h7q7t7KBp7qeKCFfvPK4VR+JrlNU+KXhHS96HVVupV/gtFMmfow+X9a+a73Ub7UpvOvry4upf78shc/rVWrVPuQ6nY9xvfjzYI5Ww0S5mHZp5Vj/QBq524+OXiKRiLew0yFT0yjsw/8eA/SvMKKrkXYXtGdrL8WfGkucawsYPZLaL/4nNUpPiP4wlbJ1+5H02r/ACArl6KfKuxPM+50f/CwPFm7P/CQXv8A39qeP4k+MYvua7cn/fVW/mDXK0U7LsHM+53dt8YPGUEoeS/huVH8E1sgB/75Cn9a37H476oh/wCJho9pOv8A07yNEf13V5LRU8q7Fcz7n0Npfxs8M3hRL2O7sXPUyR70H4rz+ld1pet6ZrMPm6bf212nGfJkDEfUdR+NfH9SW9xPaTJNbTyQyocq8bFWBHoRzSdNdBqo+p9mUtfOfh74x+INKZItR26nbjj958soHsw6/iDXsXhnx7oPilVWyuxHdYybWfCyD6Do34GsnFo0UkzqqKSlpFCUUtQXFxDaQPPcSpDCgLPJIwVUA7kngUAT00kKMngCvKvE3xq06w32+hQfbpxx50mVhH9W/SvJde8beIfEhf8AtHUZjC3/AC7RNsiH/AR1/HNWoNkOSR9C6v8AEXwroxZZ9WhllXP7q3/etkdvlyAfrXF6j8eLJGK6bos8w7PcSiP9AG/nXh1FWqaIdRnpV58bvFE6MlvBYWuejRxMzD/vpiP0rGl+KnjSbg62wH+xBEv8lrjqKrkXYjmfc6Z/iH4ududfvfwYCkHxB8XL01+9/GTNc1RTsuwcz7nXRfFDxnD9zXZj/vxRt/NTWnZfGbxda586e0vM/wDPe3A/9A2159RS5F2Dmfc9esPjzfIuL/RLeb3t5Wj/AEbd/Ouo0/42eGLtkW6jvbIn7zSRB0B+qkn9K+eaKXs0UqjPrfSvFega3tXTtWtJ3bkRiQB/++Tz+lbVfF9dDpHjzxPom0WmsXPlrwIpm8xMfRs4/CodPsWqnc+r6SvFNF+OsgYR65pasM8zWjYOP9xv/iq9K8P+M9C8TZGmXyyTBdzQOpSQD6Hr+FQ4tFppnRUUlLSGFJS0lABS0lLQAGuV+I3/ACT7Wv8Ar3P8xXVGuW+I3/JPda/69z/MULcHsfK9FFFdRyhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABTo5HhlSWJ2jkQ7lZGwQR3BptFAHq/gz4xXVgY7HxG0l3a5AW8HMsY/wBr++Pfr9a9xtbqG8tIrm2kWSGVA8br0ZSMg18b19Z+CuPA2hf9eMP/AKAKxqJI1g29Cj408c6d4NtEa6V57qYHyLePgvjuT0Ar578T+NNa8WXG/ULjEAOY7WLKxJj2zyfc13fx5/5DGj/9cH/9CFeR1VNK1xTbvYKKKK0MwooooAKKKKACiiigAooooAKKKKACiiigAr0D4Nf8lCh/69pf5V5/XoHwa/5KHB/17y/yqZ7McN0fSNFFFc50hSUtJQAUtJS0ABrlviN/yT3Wv+vc/wAxXUmuW+I3/JPda/69z/MULcHsfK9FFFdRyhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV9Z+Cv+RG0H/rxh/9AFfJlfWfgr/kRtB/68If/QBWdXoaUt2eUfHn/kMaP/1wf/0IV5HXrnx5/wCQxo//AFwf/wBCFeR06fwoUtwoooqyAooooAKKKKACiiigAooooAKKKKACiiigAr0D4Nf8lDh/695f5V5/XoHwa/5KFD/17S/yqZ7McN0fSNFFFc50hSUtJQAUtJS0ABrlviN/yT3Wv+vc/wAxXUmuW+I3/JPda/69z/MULcHsfK9FFFdRyhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV9Z+Cv+RG0H/rwh/9AFfJlfWfgr/kRtB/68If/QBWdXoaUup5R8ef+Qxo/wD1wf8A9CFeR1658ef+Qxo//XB//QhXkdOPwoUtwoooqyAooooAKKKKACiiigAooooAKKKKACiiigAr0D4Nf8lDh/695f5V5/XoHwa/5KHD/wBe8v8AKpnsxw3R9I0UUVznSFJS0lABS0lLQAGuW+I3/JPda/69z/MV1JrlviN/yT3Wv+vc/wAxQtwex8r0UUV1HKFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABX1n4K/5EbQf+vCH/ANAFfJlfWfgr/kRtC/68Yf8A0AVnV6GlLqeUfHn/AJDGj/8AXB//AEIV5HXrnx5/5DGj/wDXB/8A0IV5HTj8KFLcKKKKsgKKKKACiiigAooooAKKKKACiiigAooooAK9A+DX/JQof+vaT+Vef16B8Gv+ShQ/9e0v8qUtmOG6PpGiiiuY6QpKWkoAKWkpaAA1y3xG/wCSe61/17n+YrqTXLfEb/knutf9e5/mKFuD2Pleiiiuo5QooooAKKKKACiiigAooooAKKKKACiiigAooooAK+s/BX/IjaF/14w/+gCvkyvrLwV/yIuhf9g+H/0AVnV6GlLdnlPx5/5DGj/9cH/9CFeR1658ef8AkMaP/wBcH/8AQhXkdOPwoUtwoooqyAooooAKKKKACiiigAooooAKKKKACiiigAr0D4Nf8lCh/wCvaX+Vef16B8Gv+Shw/wDXvL/Kpnsxw3R9I0UUVznSFJS0lABS0lLQAGuW+I3/ACT3Wv8Ar3P8xXUmuW+I3/JPda/69z/MULcHsfK9FFFdRyhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV9Z+Cv8AkRtB/wCvCH/0AV8mV9Z+Cv8AkRtB/wCvCH/0AVnV6GlLqeUfHn/kMaP/ANcH/wDQhXkdeufHn/kMaP8A9cH/APQhXkdOPwoUtwoooqyAooooAKKKKACiiigAooooAKKKKACiiigAr0D4Nf8AJQ4f+veX+Vef16B8Gv8AkocP/XvL/Kpnsxw3R9I0UUVznSFJS0lABS0lLQAGuW+I3/JPda/69z/MV1JrlviN/wAk91r/AK9z/MULcHsfK9FFFdRyhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV9Z+Cv+RG0H/rwh/8AQBXyZX1n4K/5EbQf+vCH/wBAFZ1ehpSPKPjz/wAhjR/+uD/+hCvI69c+PP8AyGNH/wCuD/8AoQryOnH4UKW4UUUVZAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV6B8Gv+Shw/wDXvL/KvP69A+DX/JQ4f+veX+VTPZjhuj6RooornOkKSlpKAClpKWgANct8Rv8Aknutf9e5/mK6k1y3xG/5J7rX/Xuf5ihbg9j5XooorqOUKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvrPwV/yI2g/wDXhD/6AK+TK+s/BX/IjaD/ANeEP/oArOr0NKR5R8ef+Qxo/wD1wf8A9CFeR1658ef+Qxo//XB//QhXkdOPwoUtwoooqyAooooAKKKKACiiigAooooAKKKKACiiigAr0D4Nf8lDh/695f5V5/XoHwa/5KHD/wBe8v8AKpnsxw3R9I0UUVznSFJS0lABS0lLQAGuW+I3/JPda/69z/MV1JrlviN/yT3Wv+vc/wAxQtwex8r0UUV1HKFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABX1n4K/5EbQf+vCH/ANAFfJlfWfgr/kRtC/68Yf8A0AVnV6GlI8o+PP8AyGNH/wCuD/8AoQryOvXPjz/yGNH/AOuD/wDoQryOnH4UKW4UUUVZAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV6B8Gv+Shw/9e8v8q8/r0D4Nf8AJQ4f+veX+VTPZjhuj6RooornOkKSlpKAClpKWgANct8Rv+Se61/17n+YrqTXLfEb/knutf8AXuf5ihbg9j5XooorqOUKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvrPwV/yI2g/9eEP/oAr5Mr6z8Ff8iNoP/XhD/6AKzq9DSkeUfHn/kMaP/1wf/0IV5HXrnx5/wCQxo//AFwf/wBCFeR04/ChS3CiiirICiiigAooooAKKKKACiiigAooooAKKKKACvQPg1/yUOH/AK95f5V5/XoHwa/5KHD/ANe8v8qmezHDdH0jRRRXOdIUlLSUAFLSUtAAa5b4jf8AJPda/wCvc/zFdSa5b4jf8k91r/r3P8xQtwex8r0UUV1HKFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABX1n4K/wCRG0H/AK8If/QBXyZX1n4K/wCRG0L/AK8Yf/QBWdXoaUup5R8ef+Qxo/8A1wf/ANCFeR1658ef+Qxo/wD1wf8A9CFeR04fChS3CiiirICiiigAooooAKKKKACiiigAooooAKKKKACvQPg1/wAlDh/695f5V5/XoHwa/wCShw/9e8v8qmezHDdH0jRRRXOdIUlLSUAFLSUtAAa5b4jf8k91r/r3P8xXUmuW+I3/ACT3Wv8Ar3P8xQtwex8r0U5I2lcRxqzOxwFC5JNdho3wt8V6ztcad9jiP/LS8by//HfvfpXTdI5kmzjaK9w0r4EWibX1fVppjjmO1QIM/wC8ck/kK7jTPh74U0r/AFGi2rt/enXzT/4/nH4VDqItU2fMNlpl/qUvl2Njc3Un92CJnP6V0Vn8NPGF+u+PQ51Gcfv2WI/k5Br6gjhSGMRxIqRrwFUAACpan2jK9mj52t/gl4qmUGSXToM9RJOxI/75Uir0XwJ1okebq1go77FduPyFe90UvaMfIjw3/hQt7/0HoP8AwGb/AOKqtL8CdbBPlatYN6b96/0Ne90Uc7DkR85XfwV8WW6bovsF0f7sU5B/8fAFYF/8PPF2m/67Qrtx6wKJen+5nFfVlFHtGHIj4wlilt5THKjRyLwyupBBptfYeoaVp+qxeVqFlb3Uf92eIOP1FcHrXwW8O6grPp7T6bMemxvMjz7qxz+RFWqi6kOm+h88UV3HiH4U+JtB3SRW39o2w6SWmWbHun3vyz9a4g/K2DwRVppkNNCV9Z+Cv+RG0L/rxh/9AFfJlfWfgr/kRtC/68Yf/QBUVehdPqeUfHn/AJDGj/8AXB//AEIV5HXrnx5/5DGj/wDXB/8A0IV5HTh8IpbhRV7StG1LXLsWumWMt3MeojXIGe5PRR7mvT9A+Bt1NiXX79YEPJgtfmf6FjwPwBqnJIlRbPIqvWOi6pqhYafpl3d46+RAz7fyr6Y0f4deFtFUeRpEMsox+9uf3rZH+9kD8K6dUCKFVcAdAOBWbqdjRU+5802vwl8ZXRTdpawI38Us6DH1AJP6VrxfAzxG/Mt9pie3mSE/+g19B0VPtGVyI8Ki+A2olf32t2yt6JEzD9cUsnwH1BUzFrds7+jwMo/PJr3SilzsOVHz9L8CvEQ/1d/pj/70kg/9lNZl58HvGNu37uxt7odcxXKD/wBCwa+lKKftGHIj5KvvBviTTd/2rRL9ETlnWBmQD/eXIrEIKtgrgjtX2fWfqGh6Tqv/ACENMtLrtmaFWP5nmn7TyF7PzPj+ivo3Vvg34W1BS1rHPp8vODBIWXJ9VbP6Yrg9Y+B+t2oZ9KvIL9QOEf8AdP8AhnI/WrVREOmzy6vQPgz/AMlCh/695P5VyWreH9X0OXy9T06e1OcBpEO1sejdD+Fdb8Gf+ShQ/wDXvJ/KiWzBbo+kaKKKwNwpKWkoAKWkpaACqGr6Xba3pdzpt2rG3uE2vsbBxV+igDG0bwxovh+IJpemwW7YwXVcuR7seT+dbFLSUALRRRQAlFU7/VdO0qLzdQvra0jPRp5VQH8zXG6h8YPCFgv7q6nvW6FbeA/zbaP1p2Yro7+ivFb348j51sNCbvseefH5qB/WsK4+OHiiT/VW+nQj2iYn9WNVyMnmR9D0V80y/GDxjJ0voI/9y2T+uahT4seMg2f7Wz7GCPH8qPZsOdH05RXzfB8ZvF0RG6W0m/66QD/2XFbdh8d9SRsaho9rMOxt3aM/ruo9mw50e6UV5/pHxi8K6m6xzyz6fIcAfaY/lyf9pcgD3OK7m0u7a9t0uLW4inhb7skThlP4jipaaKumT1yfin4faF4pR3urfyLwji6g+V8+/ZvxrraKQz5d8XfDnWvCZeaVPten54u4lOBk/wAY6qf096+hPBX/ACI2hf8AXjD/AOgCtiaKO4geKVFeNwVZWXII9CO9LBBFbQJDCixxIAqoowABTcrolRseJ/HC2nvPEOh21tFJNPLC6pHGpLMSw6AVL4R+CpdY7zxNIU7ixhbn/gbj+S/nXsZtLc3a3RgjNyqlFlKjcFJ5GeuKsU+Z2sLlV7lPTtLsdJs0tNPtYreBeiRqAPr7mrtFJUli0Vzut+NPDvh3cupapBHKOsKHfJ/3yOR+NcDqXx2sY3KaZo884/v3Eoj5+gDZppNktpHsFFfPFz8bvFEx/cwadAO2ImY/q1Zkvxc8ZTNkanHH7JAn+BqvZsXOj6aor5iHxX8Zg/8AIYz/ANsI/wDCrUXxk8YR/fubaX/ftl/pij2bDnR9JUV4FafHXXI3X7ZplhNGOvl74yfxyR+ldJYfHbSJl/4mOk3ls2f+WTLKP12n9KXI+wc67nrVFcnp3xI8I6mQsWtQRMf4bjMX6sAK6eKaOeJZYnV42GVZTkEfWpsWS0UUUARSwR3ETRSoskbAhldQQQfWsKx8F+H9L1z+2LDTo7W72FCYchMHr8vQfhXRUUAFFFFABSUtJQAUtJS0AFFFIaACsvV9f0rQofN1PUbe1GMgSONzfRep/CuZ8Sab8QdUkeLSdR0rTrQ5AKs/mke7bDj8MV55cfBPxXdzPPc6rpk0rHLPJPKzE/UpTSXVktvodHrnxw0u23xaNYTXjjIEs37uP2IHLH9K891j4qeLNYZh/aLWUR/5Z2a+Xj/gXLfrW1/wovxJ/wA/+k/9/ZP/AIij/hRfiT/n/wBJ/wC/sn/xFarkRm+ZnmssstxK8s0sksrHLO7Ekn3JqOvT/wDhRfiT/n/0n/v7J/8AEUf8KL8Sf8/+k/8Af2T/AOIp80e4uV9jzCivT/8AhRfiX/n/ANJ/7+yf/EUn/Ci/Ev8Az/6T/wB/ZP8A4ijmXcOV9jzGivTv+FF+Jf8An/0n/v7J/wDEUf8ACi/Ev/P/AKT/AN/ZP/iKOZdw5X2PMaK9O/4UX4l/5/8ASf8Av7J/8RS/8KL8S/8AP/pP/f2T/wCIo5l3DlfY8wrT0XxDq/h+5+0aXfzW7H7wRsq/+8pyD+IrvP8AhRfiT/n/ANJ/7+yf/EUn/Ci/En/P/pP/AH9k/wDiKOaPcOWR1fg/4yWWouln4hRLG4OAtypPkuSe+fu/jx9K9TjkWVBJGysjDKsDkEV4D/wovxL/AM/+k/8Af2T/AOIqLRPFfiL4Y6z/AGJrcEk2nqcmEtnCk/fib09un0NQ0nsaJtbn0RRWbo+t6dr2npe6bdR3EDd0bkH0I6g+xrRrMsKKK8v8efFa20NZtM0Ro7rVPutKuGjgP/szD06evpTSuJux1/ifxjo3hS087Ubn96w/d28eGlk+i+nueK8O8UfFrX9eZ4LJ/wCzLI/wQMfMcf7T8H8sfjU9l8L/ABl4rjbWL6eGGac5/wBPdxKw9cBTgegNWv8AhRfiT/n/ANJ/7+yf/EVaUVuyG5PZHmJJZslsk96SvT/+FF+JP+f/AEn/AL+yf/EUf8KL8Sf8/wDpP/f2T/4ir5o9yOV9jzCivT/+FF+Jf+f/AEn/AL+yf/EUn/Ci/Ev/AD/6T/39k/8AiKOZdw5X2PMaK9O/4UX4l/5/9J/7+yf/ABFH/Ci/Ev8Az/6T/wB/ZP8A4ijmXcOV9jzGivTv+FF+Jf8An/0n/v7J/wDEUv8AwovxL/z/AOk/9/ZP/iKOZdw5X2PMKvadreq6O7Ppuo3NoT97yJSoOPUDg/jXoP8AwovxJ/z/AOk/9/ZP/iKT/hRfiT/n/wBJ/wC/sn/xFHNHuHLIbo3xs8Q2LKmpwW+oxdyV8qT/AL6X5f8Ax2vRtD+LvhfVykdxPJp07YG26XCZ/wB8cY9zivPP+FF+JP8An/0n/v7J/wDEUf8ACi/Ev/P/AKT/AN/ZP/iKhqD6lpyXQ98t54bmFJreVJYmGVeNgykexFTV4fpHws8eaDN5mleILC2OclUnl2k+67MH8a9T8PL4kit/L8Qtp8kq/dls2b5/qGA5+lQ12ZSZu0UlLSKCkpaSgApaSloAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigArG8Q+GdK8UaebTVLZZFGfLkHDxk91btWzRQB4Dqnw58XeC9QfUfDF1PcQDo1u2JQuejJ0cfTP0qKP4x+MNNX7NfWVo86cM1xbOj59wCB+lfQdMZAeozV83dEcvZnztceJviB4/wA2lnBOts/DpZxGOMg/3nPb2LV3PgL4Uw6BNHqetGK61BcGKJVzHAc9cn7zfy/WvUQAOlLScuiGo9WFLRRUlBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFJS0lABS0lLQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABSUtJQAUtJS0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUlLSUAFLSUtABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFJS0lABS0lLQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABSUtJQAUtRgn1p9AC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0UlFAC0lFRMTkc9qAP/Z";
		    	if(imageBlob != null) {
		    		byte[] imageData = imageBlob.getBytes(1, (int) imageBlob.length());
			    	base64Image = Base64.getEncoder().encodeToString(imageData);
		    	}
		    	books.add(new Book(stock, id, title, author, publicdate, genre, isbn, dateAdded, price, description, base64Image));
		    }
		    return books;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
		
	}
	public ArrayList<User> getAllUsers(int offset, int limit) throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Customers AS c";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<User> users = new ArrayList<User>();
		    while(rs.next()) {
		    	int custID = rs.getInt("custID");
		    	String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String imageBlob = rs.getString("custImageURL");
	            
	            User user = new User(username, email, password, imageBlob);
	            user.setUserID(custID);
	            
		    	users.add(user);
		    }
		    return users;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public String createBook(Part imageFile, String title, String author, String date, String genre, String isbn, double price, int stock, String desc) {
		byte[] imageData = null;
		if(imageFile != null) {
			try {
				imageData = convertImage(imageFile);
			} catch (Exception e) {
				imageData = null;
			}
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "INSERT INTO Books (title, author, publication_date, genre, price, stock, isbn13, description, imageBLOB) VALUES (?,?,?,?,?,?,?,?,?)";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, title);
		    ps.setString(2, author);
		    ps.setString(3, date);
		    ps.setString(4, genre);
		    ps.setDouble(5, price);
		    ps.setInt(6, stock);
		    ps.setString(7, isbn);
		    ps.setString(8, desc);
		    ps.setBytes(9, imageData);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows == 1) {
		    	return "Success";
		    }
		}catch(Exception e) {
			System.err.print(e);
		}
		
		
		return "Failed";
	}
	
	public String editBook(int id, Part imageFile, String title, String author, String date, String genre, String isbn, double price, int stock, String desc) {
		byte[] imageData = null;
		if(imageFile != null) {
			try {
				imageData = convertImage(imageFile);
			} catch (Exception e) {
				imageData = null;
			}
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "UPDATE Books SET title = ?, author = ?, publication_date = ?, genre = ?, price = ?, stock = ?, isbn13 = ?, description = ?, imageBLOB = ? WHERE id = ?";
			if(imageData == null) {
				sqlStr = "UPDATE Books SET title = ?, author = ?, publication_date = ?, genre = ?, price = ?, stock = ?, isbn13 = ?, description = ? WHERE id = ?";
				PreparedStatement ps=conn.prepareStatement(sqlStr);
			    ps.setString(1, title);
			    ps.setString(2, author);
			    ps.setString(3, date);
			    ps.setString(4, genre);
			    ps.setDouble(5, price);
			    ps.setInt(6, stock);
			    ps.setString(7, isbn);
			    ps.setString(8, desc);
			    ps.setInt(9, id);
			    int affectedRows = ps.executeUpdate();
			    if(affectedRows == 1) {
			    	return "Success";
			    }
			}else {
				PreparedStatement ps=conn.prepareStatement(sqlStr);
			    ps.setString(1, title);
			    ps.setString(2, author);
			    ps.setString(3, date);
			    ps.setString(4, genre);
			    ps.setDouble(5, price);
			    ps.setInt(6, stock);
			    ps.setString(7, isbn);
			    ps.setString(8, desc);
			    ps.setBytes(9, imageData);
			    ps.setInt(10, id);
			    int affectedRows = ps.executeUpdate();
			    if(affectedRows == 1) {
			    	return "Success";
			    }
			}
		}catch(Exception e) {
			System.err.print(e);
		}
		return "Failed";
	}
	
	public String deleteBook(int bookID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "DELETE FROM Books WHERE id = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, bookID);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows == 1) {
		    	return "Success";
		    }else {
		    	return "Error";
		    }
		}catch(Exception e) {
			return "Error";
		}
	}
	
	public User getCustomer(int userID) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Customers WHERE custID = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, userID);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	String username = rs.getString("username");
		    	String email = rs.getString("email");
		    	String password = rs.getString("password");
		    	String imageBlob = rs.getString("custImageURL");
		    	int custID = rs.getInt("custID");
		    	User user = new User(username, email, password, imageBlob);
		    	
		    	user.setUserID(custID);
		    	
		    	return user;
		    }else {
		    	throw new Exception("No User");
		    }
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void deleteCustomer(int custID) throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "DELETE FROM Customers WHERE custID = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, custID);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows != 1) {
		    	throw new Exception("Error");
		    }
		}catch(Exception e) {
			throw e;
		}
	}
	
	public static void deleteDirectory(File directory) {
	    if (directory.exists()) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteDirectory(file);
	                } else {
	                    file.delete();
	                }
	            }
	        }
	        directory.delete();
	    }
	}
	
	public void updateCustomer(int custID, String custUsername, String email, String custPassword, Part imageFile) throws Exception {
		byte[] imageData = null;
		if(imageFile != null) {
			try {
				imageData = convertImage(imageFile);
			} catch (Exception e) {
			}
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "UPDATE Customers SET username = ?, email = ?";
		    
		    if(custPassword != null) {
		    	sqlStr += ",password = ?";
		    }
		    
		    if(imageData != null) {
		    	sqlStr += ",custImageURL = ?";
		    }
		    
		    sqlStr += " WHERE custID = ?";
		    
		    PreparedStatement ps = conn.prepareStatement(sqlStr);
		    ps.setString(1, custUsername);
		    ps.setString(2, email);
		    
		    if(custPassword == null && imageData == null) {
		    	ps.setInt(3, custID);
		    }else if(custPassword != null && imageData != null) {
		    	ps.setString(3, custPassword);
		    	ps.setBytes(4, imageData);
		    	ps.setInt(5, custID);
		    }else if(custPassword == null) {
		    	ps.setBytes(3, imageData);
		    	ps.setInt(4, custID);
		    }else if(imageData == null) {
		    	ps.setString(3, custPassword);
		    	ps.setInt(4, custID);
		    }
		    
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows != 1) {
		    	throw new Exception("Error");
		    }
		}catch(Exception e) {
			System.err.print(e);
			throw e;
		}
	}
	
	public void createUser(String custUsername, String custPassword, String email, Part imageFile) throws Exception {
		byte[] imageData = null;
		if(imageFile != null) {
			try {
				imageData = convertImage(imageFile);
			} catch (Exception e) {
			}
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "INSERT INTO Customers (username, email, password, custImageURL) VALUES (?,?,?,?)";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, custUsername);
		    ps.setString(2, email);
		    ps.setString(3, custPassword);
		    ps.setBytes(4, imageData);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows != 1) {
		    	throw new Exception("Error");
		    }
		}catch(Exception e) {
			throw e;
		}
	}
	
	public byte[] convertImage(Part imageFile) throws Exception {
		byte[] imageData = null;
		String fileName = imageFile.getSubmittedFileName();
		String folderPath = "temporaryImage";
		
		if(!(fileName.contains("png") || fileName.contains("jpeg") || fileName.contains("jpg"))) {
			throw new Exception("Wrong file format");
		}else {

			String filePath = folderPath + File.separator + fileName;
			File dir = new File(folderPath);
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
			try {
				InputStream fileInputStream = imageFile.getInputStream();
		        OutputStream fileOutputStream = new FileOutputStream(filePath);
		         byte[] buffer = new byte[1024];
		         int length;
		         while ((length = fileInputStream.read(buffer)) > 0) {
		             fileOutputStream.write(buffer, 0, length);
		         }
		         fileOutputStream.close();
		         File imageToUpload = new File(filePath);
		         imageData = new byte[(int) imageToUpload.length()];
		         try (FileInputStream fis = new FileInputStream(imageToUpload)) {
		             fis.read(imageData);
		         }
		         fileInputStream.close();
		         return imageData;
		     } catch (IOException e) {
		         e.printStackTrace();
		         throw e;
		     }finally {
		    	 deleteDirectory(dir);
		     }
		}
	}
}

//Customers AS c LEFT JOIN CustomerAddress AS ca ON c.CustID = ca.customerID LEFT JOIN Address AS a ON ca.addressID = a.addressID
