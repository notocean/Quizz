create database Quiz
go

use Quiz
go

-- SQL Server database dump

-- Tạo bảng `question`
CREATE TABLE question (
    id INT IDENTITY(1, 1) PRIMARY KEY, -- Khóa chính
    question_title NVARCHAR(MAX) NOT NULL,
    option1 NVARCHAR(MAX) NOT NULL,
    option2 NVARCHAR(MAX) NOT NULL,
    option3 NVARCHAR(MAX) NOT NULL,
    option4 NVARCHAR(MAX) NOT NULL,
    right_answer NVARCHAR(MAX) NOT NULL,
    category NVARCHAR(MAX) NOT NULL
);

-- Tạo bảng `quiz`
CREATE TABLE quiz (
    id INT IDENTITY(1, 1) PRIMARY KEY, -- Khóa chính
    title NVARCHAR(255)
);

-- Tạo bảng `quiz_questions`
CREATE TABLE quiz_questions (
    quiz_id INT NOT NULL,
    questions_id INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quiz(id),
    FOREIGN KEY (questions_id) REFERENCES question(id)
);

-- Thêm dữ liệu vào bảng `question`
INSERT INTO question (question_title, option1, option2, option3, option4, right_answer, category) VALUES
(N'MDI là từ viết tắt của trường hợp nào sau đây?', N'Multiple Document Interfaces', N'Material Design Icons', N'Multi Dimensional Integration', N'Multiple Document Interface ', N'Multiple Document Interface ', 'java'),
(N'Mục đích của phương thức setDefaultCloseOperation() trong Jframe là gì?', N'Đóng cửa sổ', N'Đặt tiêu đề cho cửa sổ', N'Đặt kích thước cho cửa sổ', N'Để chỉ ra hoạt động đóng cửa sổ mặc định cho cửa sổ ', N'Để chỉ ra hoạt động đóng cửa sổ mặc định cho cửa sổ ', 'java'),
(N'Mục đích của phương thức setIcon() trong Swing là gì?', N'Đặt kích thước cho một Icon', N'Cho phép hoặc làm mất hiệu lực một Icon', N'Chỉ ra một ảnh Icon cho một thành phần GUI', N'Đặt mầu nền cho một Icon', N'Chỉ ra một ảnh Icon cho một thành phần GUI', 'java'),
(N'Giao diện bộ lắng nghe sự kiện nào được sử dụng để xử lý các sự kiện bàn phím?', N'KeyListener ', N'ActionListener', N'ItemListener', N'MouseListener', N'KeyListener ', 'java'),
(N'Kiểu đối tượng nào sau đây dùng để tổ chức các thành phần trong đối tượng chứa (container)?', N'Grid Manager', N'Layout manager', N'Event adapter', N'Event Handler', N'Layout manager', 'java'),
(N'Trường hợp nào sau đây là một đối tượng chứa (container) cung cấp khung nhìn để hiển thị một phần của thành phần lớn hơn?', N'JScrollBox', N'JscrollPane ', N'JcrollPanel', N'JScroll', N'JscrollPane ', 'java'),
(N'Câu lệnh nào sau đây để đăng ký trình nghe để xử lý thay đổi thanh trượt?', N'sl.addListener(e -&gt; {processStatements});', N'sl.valueProperty().addListener(e -&gt; {processStatements});', N'sl.getItems().addListener(e -&gt; {processStatements});', N'sl.getValue().addListener(e -&gt; {processStatements});', N'sl.valueProperty().addListener(e -&gt; {processStatements});', 'java'),
(N'Lớp nào sau đây là lớp trong JavaFX để biểu diễn một sự kiện?', N'ActionEvent', N'WindowEvent', N'MouseEvent', N'Ba trường hợp còn lại đều đúng', N'Ba trường hợp còn lại đều đúng', 'java'),
(N'Trường hợp nào trả về mục (item) được chọn trong đối tượng ComboBox cbo?', N'cbo.getSelectedIndices()', N'cbo.getSelectedIndex()', N'cbo.getSelectedItems()', N'cbo.getValue()', N'cbo.getValue()', 'java'),
(N'Phương thức nào thêm một mục vào đối tượng ConboBox cbo?', N'cbo.addObject(s)', N'cbo.add(s)', N'cbo.addChoice(s)', N'cbo.getItems().add(s)', N'cbo.getItems().add(s)', 'java'),
(N'Trong JavaFX framework sử dụng ký hiệu nào để kết nối các điều khiển trình tạo cảnh (scene buider) với lớp Java?', N'@Connect', N'@FXML ', N'@Override', N'@JFXML', N'@FXML ', 'java'),
(N'Chọn phát biểu đung cho Prism?', N'một công cụ web', N'một công cụ truyền thông', N'một thư viện đồ họa mở', N'một đường dẫn đồ họa tăng tốc phần cứng hiệu suất cao', N'một đường dẫn đồ họa tăng tốc phần cứng hiệu suất cao', 'java'),
(N'Để đăng ký nguồn cho một sự kiện hành động bằng trình xử lý, trường hợp nào sau đây sẽ được sử dụng?', N'source.setOnAction(handler)', N'source.addAction(handler)', N'source.setActionHandler(handler)', N'source.addOnAction(handler)', N'source.setOnAction(handler)', 'java'),
(N'Để xử lý sự kiện nhấn phím trên cửa sổ p (pane) cần đăng ký trình xử lý với p bằng cách sử dụng trường hợp nào sau đây?', N'p.setOnKeyClicked(handler);', N'p.setOnKeyPressed(handler);', N'p.setOnKeyTyped(handler);', N'p.setOnKeyReleased(handler);', N'p.setOnKeyPressed(handler);', 'java'),
(N'Trường hợp nào sau đây không phải là kiểu của Stage?', N'Decorated', N'Opaque', N'Utility', N'Transparent', N'Opaque', 'java'),
(N'Cách viết nào dưới đây là chính xác?', N'Thread t = new Thread(-> () {...})', N'Thread t = new Thread(() {...})', N'Thread t = new Thread(() -> {...})', N'Thread t = new Thread(() -> {...});', N'Thread t = new Thread(() -> {...});', 'java'),
(N'Chỉ ra phương thức Constructor không chính xác của Thread.', N'public Thread(String name){...}', N'public Thread(){Runnable ra} ', N'public Thread(ThreadGroup tg){...}', N'public Thread(Runnable ra, String name){...}', N'public Thread(ThreadGroup tg){...}', 'java'),
(N'Cấu trúc phương thức không chính xác?', N'public static native Thread currentThread();', N'public static native void yield();', N'public static native void sleep(long millis) throws InterruptedException;', N'public static void sleep(long millis, int nanos){...}', N'public static void sleep(long millis, int nanos){...}', 'java'),
(N'Cho khai báo: Thread t = new Thread(new MyThread()); - MyThread được cài đặt thế nào?', N'class MyThread extends Thread {...}', N'class MyThread implements Runnable {...}', N'class MyThread extends Runnable {...}', N'class MyThread implements Thread {...}', N'class MyThread implements Runnable {...}', 'java'),
(N'Cho khai báo: Runnable ra = new Runnable() {...}; - Cách viết ở phiên bản Java 8 sử dụng biểu thức lambda là:', N'Runnable ra = -> () {...};', N'Runnable ra = () -> {...}', N'Runnable ra = () -> {...};', N'Runnable ra = () {...};', N'Runnable ra = () -> {...};', 'java'),
(N'Cho khai báo: ThreadGroup tg = new ThreadGroup("Random"); - lấy ra số lượng phân tuyến đang hoạt động bằng cách:', N'tg.activeCount()', N'tg.activeGroupCount()', N'tg.getParent()', N'g.getMaxPriority()', N'tg.activeCount()', 'java'),
(N'Lệnh báo hiệu kết thúc 1 Lô là gì?', N'Go', N'Exists', N'End', N'Execute', N'Go', N'Hệ quản trị cơ sở dữ liệu'),
(N'SQL Server là một hệ thống quản trị CSDL?', N'Hướng thành phần', N'Quan hệ', N'Hướng đối tượng', N'Phân tán', N'Quan hệ', N'Hệ quản trị cơ sở dữ liệu'),
(N'SQL server là một hệ thống quản trị nhiều người dùng kiểu?', N'Ring', N'Star', N'Client – Server', N'Peer to Peer', N'Client – Server', N'Hệ quản trị cơ sở dữ liệu'),
(N'SQL Server sử dụng ngôn ngữ lập trình và truy vấn CSDL nào?', N'Transact – SQL', N'C/C++', N'Java', N'Python', N'Transact – SQL', N'Hệ quản trị cơ sở dữ liệu'),
(N'RDBMS là viết tắt của...?', N'Relational Database Management System', N'Read Database Master System', N'Real Database Management System', N'Read Database Model System', N'Relational Database Management System', N'Hệ quản trị cơ sở dữ liệu'),
(N'Quá trình trong đó một tập lệnh được xử lý cùng lúc được gọi là?', N'Order by', N'Batch', N'Having', N'Group', N'Batch', N'Hệ quản trị cơ sở dữ liệu'),
(N'SQL có nghĩa là gì?', N'Structured Query Language', N'Strict Query Language', N'Strong Query Language', N'Standard Query Language', N'Structured Query Language', N'Hệ quản trị cơ sở dữ liệu'),
(N'Thành phần nào trong SQL Server thể hiện mối quan hệ giữa các bảng?', N'Indexs', N'File Group', N'Views', N'Diagram', N'Diagram', N'Hệ quản trị cơ sở dữ liệu'),
(N'Nơi chứa các template dùng làm mẫu để tạo CSDL mới. Khi bạn tạo CSDL thì SQL Server lấy tất cả các mẫu (bao gồm Tables, Views,…) từ CSDL này, đó là?', N'Master', N'Model', N'MSDB', N'Tempdb', N'Model', N'Hệ quản trị cơ sở dữ liệu'),
(N'Với mỗi Máy chủ chúng ta có thể cài đặt bao nhiêu hệ thống SQL Server?', N'Không hạn chế', N'2', N'1', N'3', N'1', N'Hệ quản trị cơ sở dữ liệu'),
(N'Ràng buộc được sử dụng nhằm chỉ định điều kiện hợp lệ đối với dữ liệu. Mỗi khi có sự thay đổi dữ liệu trên bảng (INSERT, UPDATE), những ràng buộc này sẽ được sử dụng nhằm kiểm tra xem dữ liệu mới có hợp lệ hay không?', N'Check', N'NoCheck', N'Foreign key', N'Primary key', N'Check', N'Hệ quản trị cơ sở dữ liệu'),
(N'Mỗi bảng có ít nhất bao nhiêu khóa chính?', N'2', N'3', N'1', N'0', N'0', N'Hệ quản trị cơ sở dữ liệu'),
(N'Biến toàn cục @@ERROR có ý nghĩa gì?', N'Trả về mã lỗi đầu tiên', N'Trả về mã lỗi xảy ra gần đây nhất', N'Trả về số Record lỗi hệ thống', N'Trả về lỗi trong chương trình', N'Trả về mã lỗi xảy ra gần đây nhất', N'Hệ quản trị cơ sở dữ liệu'),
(N'Chú thích (Comments) ở trong SQL Server có dạng nào?', N'-- hoặc /*..*/', N'--', N'//', N'/* ... */', N'-- hoặc /*..*/', N'Hệ quản trị cơ sở dữ liệu');

-- Thêm dữ liệu vào bảng `quiz`
INSERT INTO quiz (title) VALUES
(N'Bài kiểm tra java 1'),
(N'Bài kiểm tra java 2'),
(N'Bài kiểm tra hệ quản trị cơ sở dữ liệu 1');

-- Thêm dữ liệu vào bảng `quiz_questions`
INSERT INTO quiz_questions (quiz_id, questions_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(2, 20),
(2, 3),
(2, 7),
(2, 5),
(2, 2),
(2, 8),
(2, 10),
(2, 14),
(2, 12),
(3, 24),
(3, 26),
(3, 27),
(3, 25),
(3, 29),
(3, 32),
(3, 31);