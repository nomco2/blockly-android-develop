


//20181118 새로 넣음
Blockly.JavaScript['control_if_start'] = function(block) {
  var value_value_input_1 = Blockly.JavaScript.valueToCode(block, 'value_input_1', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_statement_input_1 = Blockly.JavaScript.statementToCode(block, 'statement_input_1');
  // TODO: Assemble JavaScript into code variable.
  var code = "[27"+value_value_input_1+":"+statements_statement_input_1+"]";
  return code;
};

Blockly.JavaScript['equal'] = function(block) {
  Blockly.Variables.createVariable(button.getTargetWorkspace(), null, 'panda');
  var value_value1 = Blockly.JavaScript.valueToCode(block, 'value1', Blockly.JavaScript.ORDER_ATOMIC);
  var value_value2 = Blockly.JavaScript.valueToCode(block, 'value2', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = "[35,"+value_value1+":"+value_value2+"]";
// TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};