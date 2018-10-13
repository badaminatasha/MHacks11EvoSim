from enum import Enum
import random

class OrganismActions():
	class Move:
		def __init__(dx, dy):
			self.dx = dx
			self.dy = dy
	class Attack:
		def __init__(target):
			self.target = target
	class Eat:
		def __init__(target):
			self.target = target
	class Communicate:
		def __init__(target, data):
			self.target = target
			self.data = data
	class Mate:
		def __init__(target):
			self.target = target

	Actions = [Move, Attack, Eat, Communicate, Mate]

class OrganismType(Enum):
	LAND = 0
	WATER = 1

class Organism:
	MUTATION_FACTOR = 0.2

	def __init__(self, final_size, growth_rate, insulation, organism_type, decision_tree):
		self.final_size = final_size
		self.growth_rate = growth_rate
		self.insulation = insulation
		self.organism_type = organism_type
		self.decision_tree = decision_tree

	# Combines two organisms into one, assuming they are the same type
	def __init__(self, o1, o2):
		self.final_size = (o1.final_size + o2.final_size) / 2
		self.growth_rate = (o1.growth_rate + o2.growth_rate) / 2
		self.insulation = (o1.insulation + o2.insulation) / 2
		self.organism_type = o1.organism_type

		# Merge decision trees together
		self.decision_tree = DecisionTree(o1.decision_tree, o2.decision_tree)
		self.mutate()

	def mutate(self):
		mutationLower = 1 - MUTATION_FACTOR
		mutationUpper = 1 + MUTATION_FACTO
		self.final_size  *= random.uniform(mutationLower, mutationUpper)
		self.growth_rate *= random.uniform(mutationLower, mutationUpper)
		self.insulation  *= random.uniform(mutationLower, mutationUpper)

		self.decision_tree.mutate()

	def get_action(nearby_tiles, nearby_organisms, nearby_food):
		