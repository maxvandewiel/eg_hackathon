#!/bin/sh
if [ "$1" == "" ]; then
	echo "Failure :: this script exoect a parameter defining project key in SonarQube for the project this script needs to evaluate it quality gateway violations"
	echo "Usage :: ./testSonarQualityGateOutput.sh [your sonar project key]!"
	exit 1
else
	echo "parmeter for project key in sonar = $1"
fi

# example project keys:
# - nl.ns.stam.dist:stam-dist-parent
# - nl.prorail.vtb:vtb
RESULT=`curl -u builduser:builduser -s "http://s05:9000/api/resources/index/?resource=${0}&metrics=quality_gate_details&format=json"`
OUTCOME="FALSE"

if [ "$RESULT" == "[]" ]; then 
	OUTCOME="TRUE"
	echo $OUTCOME
	exit 0
else 
	echo $OUTCOME
        exit 1
fi

exit 0

