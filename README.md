# Todo List App

A **Todo List** Android application built with Kotlin. The application allows users to manage their tasks by adding, editing, and deleting records. Access control is implemented through user authentication, so only authorized users can modify the task records.

## Features

- **User Authentication**: Only authenticated users can edit the task records.
- **Task Management**: Users can add, update, and delete tasks.
- **Task Listing**: View tasks in a clean and organized interface.
- **Task Details**: View detailed information about individual tasks.
- **User-specific Task Assignment**: Assign tasks to specific users.
  
## Screenshots

### 1. Login Screen
Users authenticate by providing login credentials before accessing the app's functionality.

![Login Screen](https://github.com/user-attachments/assets/de60a7a7-b0be-4939-a81b-5296abf1fdf0)

---

### 2. Home Screen
Displays a summary of task categories and a call to action to view all tasks.

<img src="https://github.com/user-attachments/assets/538f3763-8a12-4109-940f-034eb3b62bfe" alt="Home Screen" width="359" />

---

### 3. Task List Screen
View all tasks that are accessible to the user, with options to filter, sort, and search.

![Task List](https://github.com/user-attachments/assets/a37f0305-33d2-4b22-a662-954a39a1680f)

---

### 4. User List Screen
See a list of users, which allows assigning tasks to specific individuals.

<img src="https://github.com/user-attachments/assets/170d11e4-46ab-4311-82a6-c68acbe5e6c1" alt="User List" width="359" />

---

### 5. Add Task Screen
Create a new task with title, description, and other relevant details.

<img src="https://github.com/user-attachments/assets/c4a58e4b-8cea-4617-84d5-8c151ce3b0c7" alt="Add Task" width="359" />

---

### 6. Task Detail Screen
View detailed information about a specific task, including its status, priority, and the user it is assigned to.

![Task Details](https://github.com/user-attachments/assets/052ea0a4-1626-4b95-a034-22aef0deeeed)

---

## Installation

To install and run the application locally:

1. Clone this repository:
    ```bash
    git clone https://github.com/your-repo/todo-list-app.git
    ```

2. Open the project in **Android Studio**.

3. Sync Gradle files.

4. Run the project on an emulator or physical device.

## Tech Stack

- **Kotlin**: The programming language used for Android development.
- **Firebase Authentication**: Used for managing user login and authentication.
- **Firebase Firestore**: For storing tasks and user data.
- **Material 3 Design**: For consistent and modern UI design.

## How It Works

1. **Authentication**: The app uses Firebase Authentication to ensure that only authenticated users can access and modify tasks.
2. **Task Management**: Tasks are stored in Firebase Firestore. Users can create new tasks, view existing tasks, and update or delete tasks as necessary.
3. **User Roles**: Specific roles or permissions can be set to allow only certain users to edit or assign tasks.

## Contributing

If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

## Contact

For any questions or issues, please contact:
- Email: [nicodemusalfriyanto@gmail.com](mailto:nicodemusalfriyanto@gmail.com)
- GitHub: [https://github.com/ncccdms](https://github.com/ncccdms)
