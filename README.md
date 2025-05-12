# PrivateJoinMessage

**PrivateJoinMessage** is a lightweight Minecraft plugin for Paper and Purpur that sends private, randomized join messages directly to players instead of announcing them to the entire server.

&#x20;&#x20;

## ✨ Features

* Sends a **private** join message to the player
* Messages are **randomly selected** from a configurable list
* Includes support for `"/pjm reload"` and `"/pjm list"` commands
* Integrates with **LuckPerms** for permission-based access
* Messages stored in `messages.json` file
* Supports `"quoted"` messages and placeholder `%player%`

## 📅 Installation

1. Download the latest release `.jar` from [Releases](https://github.com/yourusername/PrivateJoinMessage/releases)
2. Drop it into your server’s `plugins/` folder
3. Start or restart your server

## 📜 Configuration

The plugin automatically generates a `messages.json` file in the plugin data folder.

Example:

```json
[
  "Welcome back, %player%!",
  "Hey %player%, great to see you!",
  "Hope you brought snacks, %player%!",
  "The adventure begins again, %player%!",
  "Ready to roll, %player%?",
  "“Quotes work fine here,” said %player%."
]
```

Use `%player%` to insert the player’s name.

## 🛠️ Commands

| Command       | Description                     | Permission                  |
| ------------- | ------------------------------- | --------------------------- |
| `/pjm reload` | Reloads the messages from JSON  | `privatejoinmessage.reload` |
| `/pjm list`   | Lists all current join messages | `privatejoinmessage.list`   |

## 🧪 Building

```bash
./gradlew build
```

The resulting `.jar` file will be located in `build/libs/`.

## 🚀 Releases

To create a new GitHub release:

1. Commit your changes and tag the version:

   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. Upload the built `.jar` from `build/libs/` to the GitHub [Releases](https://github.com/yourusername/PrivateJoinMessage/releases) page.

## 📂 Folder Structure

```
src/
 └─ main/
     ├─ java/com/ethanetxyz/privatejoinmessage/
     │   └─ PrivateJoinMessage.java
     └─ resources/
         └─ plugin.yml
messages.json (generated at runtime)
```

## 📜 License

MIT License — feel free to use, modify, and contribute!

---

Made with ☕ by [**EthanetXYZ**](https://github.com/ethanetxyz)
