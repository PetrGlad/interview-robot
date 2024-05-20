## Robot script interpreter

To launch the server execute `./gradlew bootRun` then open http://127.0.0.1:8080.

## Questions, comments

* (?) Are position coordinates row,col or col,row?
* (?) Which direction is which? Australinas may disagree with you.
* (?) It is not clear, nor specified how off-field positions should be handled if the server is aware of the displayed
  field dimensions.  (stop the script? enlarge field?, shift field by showing origin coordinates?). If we automatically
  enlarge field then coordinate values range should be limited to avoid displaying extremely large fields.
* (correctness) Should we reject scripts that left the robot status unknown (e.g. consisting of only "WAIT" commands)?
  I.e. should we always require at least one POSITION command? Current implementation returns an error in that case.
* (correctness) Should we reject or warn on repeating POSITION commands? The task wording implies that only initial
  position can be set directly.
* (tests) Controller test should check that the correct parameters are passed to script runner (should probably inject
  script runner, so it can be mocked).
* (tests) Controller tests should look for specific elements instead of substrings (jQuery-like, e.g. with Jsoup). Text
  match is brittle.
* (security) Limit script size and command count size.
* (security, usability) Improve error handling (show meaningful error messages instead pf exceptions wherever possible).
* (usability) Custom error page.
* (usability) In UI show indication of cardinal directions (N, E, S, W), and coordinates of starting cell or coordinates
  along axes.

## Implementation notes.

* Display of robot orientation can be implemented with styles/rotation instead of switch/case.
* It was not specified explicitly whether only exact uppercase tokens should be accepted, so for convenience lowercase
  is accepted also.
