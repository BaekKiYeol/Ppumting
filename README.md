# Ppumting (Ppumping Dating) ๐๏ธโโ๏ธ
+ ๊ฐ์ด ์ด๋ํ๊ธฐ๋ฅผ ์ํ๋ ์ฌ๋๋ค์ ์ํ ๊ฑด๊ฐํ ๋งค์นญ ๋ง๋จ ๋ฐ ์จ๋ผ์ธ PT ์๋น์ค ์ ๊ณต ์ฌ์ดํธ
+ 2022.7.11 ~ 2022.7.15

## ํํ๋ก์ ํธ
+ ์ด 5๋ช ( [๋ณธ์ธ](https://github.com/BaekKiYeol) [์ด์ฑํธ](https://github.com/LeeSeongHo7984) [์ดํ์](https://github.com/wed456) [๋ฐ์๊ท](https://github.com/parkSangGyu98) [ํํ์ฐ](https://github.com/workhan0918))
## ์ฌ์ฉํ ๊ธฐ์  ๋ฐ ํ๊ฒฝ
+ Window
+ eclipse
+ HTML/CSS3
+ Java
+ Servlet
+ Jsp
+ MySQL
+ Javascript
+ bootstrap

## ๊ตฌํ ๊ธฐ๋ฅ
+ ์ฌ์ฉ์
  + ํ์๊ฐ์, ํํด, ๋ก๊ทธ์ธ, ๋ก๊ทธ์์, ๊ฐ์ธ์ ๋ณด ์์ 
  + ๊ฒ์ํ ๋ฑ๋ก, ์์ , ์ญ์ , ์กฐํ
  + ํฌ์ธํธ ๊ณ์ข ์์ฑ, ํฌ์ธํธ ์กฐํ, ์ถฉ์ , ์ฐจ๊ฐ
  + ์ชฝ์ง ๋ฐ์ก, ๋ต์ฅ, ์กฐํ, ์ญ์ 
+ ํธ๋ ์ด๋
  + ํ์๊ฐ์, ๋ก๊ทธ์ธ, ๋ก๊ทธ์์, ์กฐํ
   
## ๋ด๋น ๊ธฐ๋ฅ
+ ํธ๋ ์ด๋ ๋ถ๋ถ
  + ํ์๊ฐ์, ํํด, ๋ก๊ทธ์ธ, ๋ก๊ทธ์์, ๊ฐ์ธ์ ๋ณด ์์ 
  
## ์ฃผ์ ์ฝ๋
### ์ฌ์ฉ์ ๋ถ๋ถ
 + ๊ฐ์ธ์ ๋ณด ์์ 
   1. ์์  ํด๋ฆญ ์ ํ์ฌ ๋ก๊ทธ์ธ๋์ด์๋ ์์ด๋๋ฅผ ์ด์ฉํ์ฌ ๊ณ ๊ฐ ์ ๋ณด๋ฅผ ๊ฐ์ ธ์ ํ๋ฉด์ ํ์
   2. ์๋ฃ ํด๋ฆญ ์ ๋น์นธ์ด ์๋์ง ์ ํจ์ฑ ๊ฒ์ฌ ํ ์ ๋ณด๋ค์ ์๋ก์ด ๊ฐ์ฒด๋ก ๋ฐ์
   3. ์๋ก์ด ๊ฐ์ฒด๋ฅผ ์๋ฐ์ดํธ
 
			UserUpdateServlet ์ผ๋ถ

			protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");

			String userId = request.getParameter("userId");

			HttpSession session = request.getSession(false);
			session.setAttribute("userId", userId);
			response.sendRedirect("../home");


			String pw = request.getParameter("pw");
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			String addr = request.getParameter("addr");

			List<String> errorMsgs = new ArrayList<>();

			if(pw == null || pw.length() == 0) {
				errorMsgs.add("๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}else if(name == null || name.length() == 0) {
				errorMsgs.add("์ด๋ฆ์ ์๋ ฅํด์ฃผ์ธ์");
			}else if(phone == null || phone.length() == 0) {
				errorMsgs.add("์ ํ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}else if(addr == null || addr.length() == 0) {
				errorMsgs.add("์ฃผ์๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}

			User user = new User();
			user.setUserId(userId);
			user.setPw(pw);
			user.setName(name);
			user.setPhone(phone);
			user.setAddr(addr);

			userService.updateUser(user);
			request.setAttribute("user", user);
			}

+ ํฌ์ธํธ ๊ณ์ข ์์ฑ
  1. ํ์๊ฐ์์ ์๋์ผ๋ก ๋๋คํ ์ซ์์ ํฌ์ธํธ๊ฐ 0์ธ ๊ณ์ข๋ฅผ ์์ฑ
  2. ๋ณธ์ธ ๊ณ์ข๋ฒํธ๋ ์ถฉ์ ํ  ๊ฒฝ์ฐ ํ์ธ ๊ฐ๋ฅ
  
			PointDao ์ผ๋ถ

			public String createAccountNum(String userId) { 
			String sql = "INSERT INTO Point (point, accountNum, userId) VALUES (0, ?, ?)";

			String numStr = String.valueOf((int) (Math.random() * 1000000000));
			StringBuilder sb = new StringBuilder();
			sb.append(numStr.substring(0, 3));
			sb.append("-");
			sb.append(numStr.substring(3, 5));
			sb.append("-");
			sb.append(numStr.substring(5));

			String result = sb.toString();

			try {
				Connection con = datasource.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				try {
					if (pointdao.isValidUser(userId)) { 
						if( pointdao.checkAccountNum(userId) == null ) { //์์ฑํด์ฃผ๊ธฐ
							stmt.setString(1, result);
							stmt.setString(2, userId);
							stmt.executeUpdate();
						}else { 
							result = null;
						}
					} else {
						result = null;
					}
				} finally {
					stmt.close();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
			}
	
+ ์ชฝ์ง ์ญ์ 
  1. ๋ฐ์ ๋ณด๊ดํจ์์ ์ชฝ์ง๋ฅผ ์ญ์ ํ๋ฉด ์๋๋ฐฉ์ ๋ณด๋ธ ์ชฝ์งํจ์์๋ ์ญ์ ๊ฐ ๋๋ ์ํฉ์ด ๋ฐ์ํ์ฌ ๋ฐ๋์ฌ๋, ๋ณด๋ธ์ฌ๋์ DB๋ฅผ ๊ฐ๊ฐ ๋ง๋ค์ด์ ํด๊ฒฐ
	
			Note.sql ์ผ๋ถ

			CREATE TABLE RcvNotes (
			no			BIGINT		 	PRIMARY KEY	AUTO_INCREMENT,
			sentid 		VARCHAR(20) 	NOT NULL	DEFAULT	'',
			userId		VARCHAR(20)		NOT NULL	DEFAULT	'',
			title		VARCHAR(100)	NOT NULL 	DEFAULT '',
			msg			VARCHAR(200)	NOT NULL 	DEFAULT '',
			sentDate 	TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
			sendnote	BOOLEAN			NOT NULL	DEFAULT FALSE
			);

			CREATE TABLE SendNotes (
			no			BIGINT		 	PRIMARY KEY	AUTO_INCREMENT,
			userId 		VARCHAR(20) 	NOT NULL	DEFAULT	'',
			recvid		VARCHAR(20)		NOT NULL	DEFAULT	'',
			title		VARCHAR(100)	NOT NULL 	DEFAULT '',
			msg			VARCHAR(200)	NOT NULL 	DEFAULT '',
			sentDate 	TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
			sendnote	BOOLEAN			NOT NULL	DEFAULT TRUE
			);
    
+ ํ์๊ฐ์
  1. ์๋ ฅํ ์ ๋ณด๋ค์ ๊ณต๋ฐฑ ์ฌ๋ถ๋ฅผ ํ์ธ
  2. ๊ณต๋ฐฑ์ด ์๋ค๋ฉด ์๋ฌ๋ฉ์์ง๋ฅผ ๋ฆฌ์คํธ๋ก error.jsp์ ๋๊ฒจ alert๋ฅผ ํ์
  3. ๊ณต๋ฐฑ์ด ์๋ค๋ฉด ์๋ก์ด User๊ฐ์ฒด์ ์ ๋ณด๋ค์ ์ ์ฅํ ๋ค DB์ ๊ฐ์ฒด๋ฅผ ์ ์ฅ
			
			UserServlet ์ผ๋ถ

			protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");

			String userId = request.getParameter("userId");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String ssn = request.getParameter("ssn");
			String phone = request.getParameter("phone");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");

			List<String> errorMsgs = new ArrayList<>();
			if(userId == null || userId.length() == 0) {
				errorMsgs.add("id๋ฅผ ์๋ ฅํด์ฃผ์ธ์,");		
			}else if(pw == null || pw.length() == 0) {
				errorMsgs.add("๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}else if(name == null || name.length() == 0) {
				errorMsgs.add("์ด๋ฆ์ ์๋ ฅํด์ฃผ์ธ์");
			}else if(ssn == null || ssn.length() == 0) {
				errorMsgs.add("์ฃผ๋ฏผ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}else if(phone == null || phone.length() == 0) {
				errorMsgs.add("์ ํ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}else if(addr1 == null || addr1.length() == 0 || 
					addr2 == null || addr2.length() == 0) {
				errorMsgs.add("์ฃผ์๋ฅผ ์๋ ฅํด์ฃผ์ธ์");
			}

			RequestDispatcher dispatcher = null;
			if(errorMsgs.size() > 0) {
				dispatcher = request.getRequestDispatcher("error.jsp");
				request.setAttribute("errorMsgs", errorMsgs);
				dispatcher.forward(request, response);
				return;
			}


			User user = new User();
			user.setUserId(userId);
			user.setPw(pw);
			user.setName(name);
			user.setSsn(ssn);
			user.setPhone(phone);
			user.setAddr(addr1+ " " + addr2);
			Userservice userService = new Userservice();
			userService.addUser(user);
			pointService.createAccountNum(userId);
			request.setAttribute("user", user);
			response.sendRedirect("../loginout/login");
			return;
			}	


+ ๊ฒ์ํ ์์ 
  1. ๊ฒ์ํ์์ ์กฐํ๋๋ ๊ธ ์ค์์ ๋ณธ์ธ ๊ธ๋ง ์์ ํ  ์ ์์ด์ผ ํ๋๋ฐ ๋ชจ๋  ๊ธ์ ์์ ํ  ์ ์๋ ์ด์ ๋ฐ์
  2. ์ธ์์ผ๋ก ํ์ฌ ๋ก๊ทธ์ธ ๋์ด์๋ ์์ด๋์ ๊ฒ์๊ธ ๊ธ์ด์ด ์์ด๋๋ฅผ ๋น๊ตํ์ฌ ํด๊ฒฐ
  
  			SelectQnaSevlet ์ผ๋ถ
  	
  			protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
			Qna qnas = new Qna();
			qnas = service.viewQna(request.getParameter("qnaNo"));
			HttpSession session = request.getSession(false);
			String userId = (String) session.getAttribute("userId");

			if(qnas.getUserId().equals(userId)) { //๊ฒ์๊ธ ์ฃผ์ธ์ด๋ผ๋ฉด
				request.setAttribute("qnas", qnas);	// "qnas"(jsp์์ ๋ฟ๋ ค์ฃผ๋ ์ด๋ฆ)๋ ํค, qnas๋ ๊ฐ
				request.setAttribute("check", true);
				request.getRequestDispatcher("selectNoQna.jsp").forward(request, response);
			}else {
				request.setAttribute("qnas", qnas);
				request.setAttribute("check", false);
				request.getRequestDispatcher("selectNoQna.jsp").forward(request, response);
			}
			}
			
			
			selectNoQna.jsp ์ผ๋ถ
			
			<c:if test="${check == false}">
			<div
			style="width: 100%; height: 50px; display: flex; text-align: center; font-size: 30px; line-height: 38px;">
			<a class="addBu" onclick="back()" style="text-decoration: none;">๋ค๋ก๊ฐ๊ธฐ</a>
			</div>
			</c:if>
			<c:if test="${check == true}">
			<form action="modifyQna.do" method="post">
				<!--  <button type="submit" value="${qnas.qnaNo}" name="qnaNo">์์ </button> -->
				<button type="submit" value="${qnas.qnaNo}" name="qnaNo"
					onclick="location.href='modifyQna.jsp'" class="addBu">
					<span style="font-size: 30px; line-height: 38px;" class="ft">์์ </span>
				</button>
			</form>
			<form action="deleteQna.do" method="post">
				<button type="submit" value="${qnas.qnaNo}" name="qnaNo"
					class="addBu">
					<span style="font-size: 30px; line-height: 38px;" class="ft">์ญ์ </span>
				</button>
			</form>
			<div
				style="width: 100%; height: 50px; display: flex; text-align: center; font-size: 30px; line-height: 38px;">
				<a class="addBu" onclick="back()" style="text-decoration: none;">๋ค๋ก๊ฐ๊ธฐ</a>
			</div>
			</c:if>
			

 ## ๊ตฌํ ํ๋ฉด
 ### ์ฌ์ฉ์, ํธ๋ ์ด๋ ๋ก๊ทธ์ธ

 ![image](https://user-images.githubusercontent.com/103983349/185534090-186fb9dd-14db-476a-99d6-767c419657e0.png)
 ![image](https://user-images.githubusercontent.com/103983349/185536667-5fd94a8f-6a27-4d2d-b691-b8d2d3af2e88.png)


 ### ์ฌ์ฉ์, ํธ๋ ์ด๋ ํ์๊ฐ์
 
 ![image](https://user-images.githubusercontent.com/103983349/185534159-e81b5b53-f5f6-48bd-98bc-e588f7efb38d.png)
 ![image](https://user-images.githubusercontent.com/103983349/185536890-5a201228-3bdc-4ee6-86db-4ffbdbec62e6.png)
 
 ### ๋ฉ์ธ
 
 ![image](https://user-images.githubusercontent.com/103983349/185534293-83c2eee3-b87b-4329-8315-f73677eda0a8.png)
 
 ### ํ์์ ๋ณด ์์ 
 
 ![image](https://user-images.githubusercontent.com/103983349/185534413-484fbfb8-d355-414c-8fb9-67dcdc1c69fa.png)

 
 ### ๊ฒ์ํ
 
 ![image](https://user-images.githubusercontent.com/103983349/185874733-2b10934f-d014-4070-b492-d40070b78ec6.png)
 
 ### ๊ฒ์๊ธ ๋ฑ๋ก
 
 ![image](https://user-images.githubusercontent.com/103983349/185874804-1c530035-1447-4870-acaf-c11654eaf3b6.png)
 
 ### ๊ฒ์๊ธ ๋ณด๊ธฐ
 
 ![image](https://user-images.githubusercontent.com/103983349/185874881-3cb1a517-a515-4eb4-a204-a5b3be22baee.png)
 
 ### ๊ฒ์๊ธ ์์ 
 
 ![image](https://user-images.githubusercontent.com/103983349/185874920-0d98e605-e7fa-409b-a39b-4ab157a7c575.png)
 
 ### P.P.T
 
 ![image](https://user-images.githubusercontent.com/103983349/185537275-85d5be18-0fe8-4f87-af95-578e83c5e30a.png)
 
 ### ์ถฉ์ 
 
 ![image](https://user-images.githubusercontent.com/103983349/185534437-c0321692-2853-4600-8bd7-35ebeef06e5b.png)

