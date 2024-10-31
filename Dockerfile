# Sử dụng image Ubuntu và cài đặt g++
FROM ubuntu:latest

# Cập nhật và cài đặt g++
RUN apt-get update && apt-get install -y g++

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép toàn bộ mã nguồn từ thư mục hiện tại của máy chủ vào thư mục /app trong container
COPY . /app

# Biên dịch mã nguồn thành file thực thi tên main
RUN g++ -o main main.cpp

# Thiết lập lệnh mặc định để chạy file thực thi
CMD ["./main"]
