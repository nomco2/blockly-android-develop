


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



Blockly.JavaScript['coding_for'] = function(block) {
  var number_v1_numberic = block.getFieldValue('v1_numberic');
  var statements_v2_statement = Blockly.JavaScript.statementToCode(block, 'v2_statement');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['coding_if'] = function(block) {
  var value_v1_value = Blockly.JavaScript.valueToCode(block, 'v1_value', Blockly.JavaScript.ORDER_ATOMIC);
  var dropdown_v2_dropdown = block.getFieldValue('v2_dropdown');
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_v1_statement = Blockly.JavaScript.statementToCode(block, 'v1_statement');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_upside'] = function(block) {
  var number_v1_num = block.getFieldValue('v1_num');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_down'] = function(block) {
  var number_v1_num = block.getFieldValue('v1_num');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_right'] = function(block) {
  var number_v1_num = block.getFieldValue('v1_num');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_left'] = function(block) {
  var number_v1_num = block.getFieldValue('v1_num');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_backward'] = function(block) {
  var number_v1_num = block.getFieldValue('v1_num');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_forward'] = function(block) {
  var number_v1_num = block.getFieldValue('v1_num');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_start'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['drone_land'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['text_input'] = function(block) {
  var text_v1_text = block.getFieldValue('v1_text');
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['coding_while'] = function(block) {
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_v2_statement = Blockly.JavaScript.statementToCode(block, 'v2_statement');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['delivery_address'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['delivery_ask'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['dust_clean'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['dust_sensing'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['fire_suppression'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['fire_detect'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};