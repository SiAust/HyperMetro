# HyperMetro

HyperMetro is a CLI to link and display metro lines and their stations.

Input is a `.json` file which is deserialized and converted into a Metro
object. From there a doubly linked list of all stations is created.

## Functions
The user may carry out some functions on the DLL.

- `/append <line> <station>` = Add a station to the end of the line
- `/add-head <line> <station>` = Add a station to the start of the line
- `/remove <line> <station>` = Remove station
- `/output <line>` = Outputs the line to the console

## Technology
Written in Java and built with Gradle. Googles GSON library is used
to deserialize the JSON.

## JSON File Structure
The `.json` file should be structured in this way -

```json
{
  "Metro-Railway": {
    "3": {
      "name": "Baker street",
      "transfer": [
        {
          "line": "Hammersmith-and-City",
          "station": "Baker street"
        }
      ]
    },
    "1": {
      "name": "Bishops-road",
      "transfer": []
    },
    "2": {
      "name": "Edgver road",
      "transfer": []
    }
  }
}
```
