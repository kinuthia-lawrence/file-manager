# 📂 File Upload and Download with Spring Boot | Efficient File Manager REST API | Multipart Download

This **Spring Boot** application provides an efficient **file management system**, enabling users to **upload**, **download**, and **list files**. It features a RESTful API with **multipart file upload and optimized download handling**, allowing files to be retrieved in chunks for faster performance. The application also includes a **Thymeleaf-based UI** for easy file management.
---

## 🚀 Features

✔ Upload files  
✔ Download files  
✔ List files

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.4.3**
- **Thymeleaf**
- **Maven**

---

## 📌 Getting Started

### ✅ Prerequisites

- **Java 21**
- **Maven**

### 🏗 Installation

1️⃣ **Clone the repository:**
```sh
git clone https://github.com/your-username/file-manager.git
cd file-manager
```

2️⃣ **Build the project:**
```sh
mvn clean install
```

3️⃣ **Run the application:**
```sh
mvn spring-boot:run
```

🔹 The application will start on **port 9090**.

---

## ⚙️ Configuration

The storage directory is configured in the `FileStorageService` class:
```java
public static final String STORAGE_DIRECTORY = "C:\\Users\\USER\\Documents\\Storage";
```
📌 You can change this path to your desired storage location.

---

## 🎯 Usage

### 📤 Uploading Files
1. Navigate to **[http://localhost:9090/uploader](http://localhost:9090/uploader)**
2. Select a file to upload and click the **"Upload"** button.

### 📥 Downloading Files
1. Navigate to **[http://localhost:9090/files](http://localhost:9090/files)**
2. Click the **"Download"** button next to the file you want to download.

### 📋 Listing Files
- Navigate to **[http://localhost:9090/files](http://localhost:9090/files)** to see the list of available files.

---

## 📂 Project Structure

```
📦 src/main/java/com/larrykin/file_manager
 ┣ 📜 Application.java                # Main application class
 ┣ 📂 controller
 ┃ ┣ 📜 FileManagerController.java     # REST controller for file operations
 ┃ ┗ 📜 FileManagerGuiController.java  # Controller for rendering Thymeleaf views
 ┣ 📜 FileStorageService.java          # Service for handling file storage
📂 src/main/resources
 ┣ 📂 templates                        # Thymeleaf templates for UI
 ┗ 📜 application.properties           # Application configuration
```

---

## 📜 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

🚀 **Happy Coding!** 🎉