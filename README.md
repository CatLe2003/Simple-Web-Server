# Simple Web Server
1. Kịch bản giao tiếp
1.1. Giao thức trao đổi: 
• Web server nhận thông điệp từ client, sau khi đọc và phân tích thông điệp thì server 
sẽ gửi dữ liệu theo yêu cầu đã phân tích được.
1.2. Cấu trúc thông điệp:
• Các dòng trong thông điệp http được ngăn cách bằng chuỗi “\r\n”.
• Thông điệp http gồm 3 phần:
o Dòng đầu tiên: miêu tả gói tin.
+ Đối với request: miêu tả yêu cầu cần được thực hiện. 
Ví dụ: GET /index.html HTTP/1.1
+ Đối với response: mã trạng thái sau khi đã web server đã phân tích yêu cầu.
Ví dụ: HTTP/1.1 404 Not Found.
o Tập các headers để miêu tả dữ liệu ở phần thân của gói tin hoặc miêu tả tính 
chất của gói tin. Mỗi header được viết hoàn toàn trên một dòng.
Ví dụ:
+ Content-Length: …
+ Connection: …
+ Content-Type: …
o Phần thân của gói tin có thể có hoặc không chứa dữ liệu cần gửi tùy vào loại 
thông điệp.
1.3. Kiểu dữ liệu của thông điệp:
• Dữ liệu gửi đi và dữ liệu nhận vào đều là kiểu chuỗi ký tự (string).
2. MÔI TRƯỜNG LẬP TRÌNH
• Ứng dụng được viết trên hệ điều hành Window 11.
• Các dữ liệu được lưu dưới dạng file *.java.
• Web chứa dữ liệu được đặt tại địa chỉ: localhost:8080.
• Ngôn ngữ lập trình: Java.
• Môi trường lập trình: Eclipse và Visual Studio Code.
• Thư viện hỗ trợ: Các thư viện có sẵn trong của Java.
3. HƯỚNG DẪN SỬ DỤNG CHƯƠNG TRÌNH
- Chạy chương trình WebServer.
- Truy cập vào port 8080 của máy đang chạy chương trình bằng browser với các test cases:
• localhost:8080
• localhost:8080/index.html
• http://<ip>:8080
• http://<ip>:8080/index.html
- Đăng nhập bằng cách điền vào form với Username: admin và Password: 123456.
+ Nếu đăng nhập đúng trả về nội dung của trang image.html.
+ Nếu đăng nhập sai trả về 401 Unauthorized.
- Web Server cho phép nhiều lượt đăng nhập từ nhiều client và một client có thể gửi một 
hoặc nhiều yêu cầu.
