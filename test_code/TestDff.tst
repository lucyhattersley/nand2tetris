	
load TestDff.hdl,
output-file TestDff.out,
output-list time%S2.4.0 in%B3.1.3
    dffOut%B4.1.3;
output;

set in 1,
tick, output;
tock, output;
tick, output;
tock, output;

set in 0,
tick, output;
tock, output;
tick, output;
tock, output;

tick, output;
set in 1,
tock, output;
tick, output;
tock, output;

tick, output;
set in 0,
tock, output;
tick, output;
tock, output;