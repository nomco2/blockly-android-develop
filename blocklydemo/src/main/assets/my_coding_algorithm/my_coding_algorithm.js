Blockly.JavaScript['control_while_loop'] = function(block) {
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['digital_pin_on'] = function(block) {
  var dropdown_digital_select = block.getFieldValue('digital_select');
  // TODO: Assemble JavaScript into code variable.
  var code = "'digital_on':'" + dropdown_digital_select +"'";
  return code;
};





Blockly.JavaScript['control_times_loop'] = function(block) {
  var number_loop_time = block.getFieldValue('loop_time');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = "'for':[{'times':" + number_loop_time +"},{'control_type':1},{" + statements_name + "}]";
  return code;
};


Blockly.JavaScript['control_unlimited_loop'] = function(block) {
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = "'for':[{'infinity':1},{'control_type':1},{" + statements_name + "}]";
  return code;
};

Blockly.JavaScript['control_while_loop'] = function(block) {
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = "'for':[{" + value_name + "},{'control_type':1},{" + statements_name + "}]";
  return code;
};