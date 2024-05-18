## Questions, comments

* (?) Are position coordinates row,col or col,row?
* (?) Which direction is which? Australinas may disagree with you.
* (?) It is not clear, nor specified how off-field positions should be handled if the server is aware of the displayed
  field dimensions.  (stop the script? enlarge field?, shift field by showing origin coordinates?). If we automatically
  enlarge field then coordinate values range should be limited to avoid displaying extremely large fields.
* (correctness) Should we reject scripts that left the robot status unknown (e.g. consisting of only "WAIT" commands)?
  I.e. should we always require at least one "POSITION" command?
* (tests) Controller test should check that the correct parameters are passed to script runner (should probably inject
  script runner, so it can be mocked).
* (tests) Controller tests should look for specific elements instead of substrings (jQuery-like, e.g. with Jsoup). Text
  match is brittle.
* (correctness) Warn on resetting position. The task wording implies that only initial position can be set directly.
* (security) Limit script size and command count size.
* (security, usability) Improve error handling (show meaningful error messages instead pf exceptions wherever possible).
* (usability) Error page.

## Implementation notes.

* Display of robot orientation can be implemented with styles/rotation instead of switch/case.
* It was not specified explicitly whether only exact uppercase tokens should be accepted, so for convenience lowercase
  is accepted also. 
