

Blockly.JavaScript['digital_pin_on'] = function(block) {
  var dropdown_digital_select = block.getFieldValue('digital_select');
  // TODO: Assemble JavaScript into code variable.
  var code = "'digital_on':'" + dropdown_digital_select +"'";
  return code;
};


Blockly.JavaScript['equal'] = function(block) {
  var value_name1 = Blockly.JavaScript.valueToCode(block, 'NAME1', Blockly.JavaScript.ORDER_ATOMIC);
  var value_name2 = Blockly.JavaScript.valueToCode(block, 'NAME2', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = "{'equal':[{'" + value_name1 + "':'" + value_name2 + "'}]};";
// TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};



//Blockly.JavaScript['control_while_loop'] = function(block) {
//  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
//  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
//  // TODO: Assemble JavaScript into code variable.
//  var code = "'for':[{" + value_name + "},{'control_type':1},{" + statements_name + "}]";
//
//  return  [code, Blockly.JavaScript.ORDER_NONE];
//};


Blockly.JavaScript['control_while_loop'] = function(block) {
  var value_name1 = Blockly.JavaScript.valueToCode(block, 'NAME1', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = "'for':[{" + value_name1 + "},{'control_type':1},{" + statements_name + "}]";
  return code;
};





Blockly.JavaScript['number_input'] = function(block) {
  var number_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = number_name;
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['string_input'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = text_name;
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};


//if 계열

Blockly.JavaScript['control_if_start'] = function(block) {
  var value_value_input_1 = Blockly.JavaScript.valueToCode(block, 'value_input_1', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_statement_input_1 = Blockly.JavaScript.statementToCode(block, 'statement_input_1');
  // TODO: Assemble JavaScript into code variable.
  var code = "{'if':[{" + value_value_input_1 + "},{'control_type':1},{" + statements_statement_input_1 + "}]};";
  return code;
};

Blockly.JavaScript['control_iot_data_start'] = function(block) {
  var value_value_input_1 = Blockly.JavaScript.valueToCode(block, 'value_input_1', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_statement_input_1 = Blockly.JavaScript.statementToCode(block, 'statement_input_1');
  // TODO: Assemble JavaScript into code variable.
  var code = "{'if_iot_data':[{" + value_value_input_1 + "},{'control_type':1},{" + statements_statement_input_1 + "}]};";
  return code;
};


//for 계열
Blockly.JavaScript['control_times_loop'] = function(block) {
  var number_loop_time = block.getFieldValue('loop_time');
  var statements_statement_input_1 = Blockly.JavaScript.statementToCode(block, 'statement_input_1');
  // TODO: Assemble JavaScript into code variable.
  var code = "'for':[{'times':" + number_loop_time +"},{'control_type':1},{" + statements_statement_input_1 + "}]";
  return code;
};

Blockly.JavaScript['control_unlimited_loop'] = function(block) {
  var statements_statement_input_1 = Blockly.JavaScript.statementToCode(block, 'statement_input_1');
  // TODO: Assemble JavaScript into code variable.
  var code = "'{for':[{'infinity':1},{'control_type':1},{" + statements_statement_input_1  + "}]}";
  return code;
};