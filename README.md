By givign a player or group of players the permission permguard.join you allow them to join the server.
Examples:
GroupZ has permguard.join true for context server=hungergames
Player A has permguard.join true for context server=dropper
Player A has permguard.join false for context server=jump_and_run
Player A has Group Z
Player A tries to join the server hungergames and succeeds.
Player A tries to join server jump_and_run and gets kicked.
Player A tries to join server dropper and succeeds.
Player A tries to join server survival and gets kicked because the permission is not set for them.
