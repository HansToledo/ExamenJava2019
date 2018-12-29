USE Kustwacht;
CREATE USER 'commander'@'localhost' IDENTIFIED BY 'Zeeslag123';
GRANT ALL PRIVILEGES ON *.* TO 'commander'@'localhost';
ALTER USER 'commander'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Zeeslag123';
flush privileges;