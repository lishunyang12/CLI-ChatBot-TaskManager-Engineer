# Engineer User Guide

Engineer is a Personal Assistant Chatbot that helps users manage tasks through a command line interface (CLI).

*Acknowledgement: This guide was adapted from the work of Thomas Chiu ([GitHub: paklongchiu/ip](https://github.com/paklongchiu/ip))*

## Table of Contents
1. [Task Types](#task-types)
2. [Task Management](#task-management)
3. [Task Viewing](#task-viewing)
4. [Exiting the Program](#exiting-the-program)

## Task Types

Engineer supports three types of tasks:

### 1. ToDos (`T`)
Simple tasks without specific dates/times.

**Format:** `todo <description>`  
**Example Input:**  
`todo buy groceries`

**Example Output:**  
Got it. I've added this task:  
[T][ ] buy groceries  
Now you have 3 tasks in the list.

### 2. Deadlines (`D`)
Tasks with a due date/time.

**Format:** `deadline <description> /by <deadline>`  
**Example Input:**  
`deadline submit report /by 15/11/2023 5pm`

**Example Output:**  
Got it. I've added this task:  
[D][ ] submit report (by: 15/11/2023 5pm)  
Now you have 4 tasks in the list.

### 3. Events (`E`)
Tasks with start and end times.

**Format:** `event <description> /from <start> /to <end>`  
**Example Input:**  
`event team meeting /from 10am /to 11am`

**Example Output:**  
Got it. I've added this task:  
[E][ ] team meeting (from: 10am to: 11am)  
Now you have 5 tasks in the list.

## Task Management

### Marking Tasks
- **Mark as done:** `mark <index>`  
  **Example Input:** `mark 2`  
  **Example Output:**  
  Nice! I've marked this task as done:  
  [D][X] submit report (by: 15/11/2023 5pm)

- **Mark as not done:** `unmark <index>`  
  **Example Input:** `unmark 1`  
  **Example Output:**  
  OK, I've marked this task as not done yet:  
  [T][ ] buy groceries

### Deleting Tasks
**Format:** `delete <index>`  
**Example Input:** `delete 3`  
**Example Output:**  
Noted. I've removed this task:  
[E][ ] team meeting (from: 10am to: 11am)  
Now you have 4 tasks in the list.

## Task Viewing

### List All Tasks
**Format:** `list`  
**Example Output:**  
Here are the tasks in your list:  
1.[T][ ] buy groceries  
2.[D][X] submit report (by: 15/11/2023 5pm)  
3.[T][ ] call mom  
4.[E][ ] project review (from: 2pm to: 3pm)  
5.[D][ ] pay bills (by: 20/11/2023)

### Find Tasks
Search for tasks containing a keyword.

**Format:** `find <keyword>`  
**Example Input:** `find project`  
**Example Output:**  
Here are the matching tasks in your list:  
1.[E][ ] project review (from: 2pm to: 3pm)  
2.[D][ ] project submission (by: 30/11/2023)

## Exiting the Program
**Format:** `bye`  
**Example Output:**  
Bye. Hope to see you again soon!  
Process finished with exit code 0

---

**Note:** All dates should be in DD/MM/YYYY format and times in 12-hour format (am/pm).
